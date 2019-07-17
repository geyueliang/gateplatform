package com.wxhx.gate.plat.service.impl;

import java.io.InputStreamReader;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.wxhx.basic_client.common.HXCoreUtil;
import com.wxhx.basic_client.common.HXLogUtil;
import com.wxhx.gate.plat.dao.CallJyLogMapper;
import com.wxhx.gate.plat.dao.FunctionMapper;
import com.wxhx.gate.plat.dao.entity.CallJyLog;
import com.wxhx.gate.plat.service.ICallJYLogService;

@Service
public class CallJYLogServiceImpl implements ICallJYLogService{
	
	private static Logger logger =  LoggerFactory.getLogger(CallJYLogServiceImpl.class);

	@Autowired
	private FunctionMapper functionMapper;
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private CallJyLogMapper callJyLogMapper;
	
	
	
	@PostConstruct
	public void initCreateLogTable() {
		//当前表不存在 执行脚本创建表
		if(functionMapper.tableExit("CALL_JY_LOG")==0) {
			//获取sql脚本
			ClassPathResource createResource = new ClassPathResource("sql/CALL_JY_LOG.sql");
//			ClassPathResource updateResource = new ClassPathResource("sql/CALL_JY_LOG.sql");
			try {
				InputStreamReader reader1 =  new InputStreamReader(createResource.getInputStream(), "utf-8");
//				InputStreamReader reader2 =  new InputStreamReader(updateResource.getInputStream(), "utf-8");
				Connection conn = sqlSession.getConfiguration().getEnvironment().getDataSource().getConnection();
				ScriptRunner scriptRunner = new ScriptRunner(conn);
	            scriptRunner.setLogWriter(null);
	            scriptRunner.setErrorLogWriter(null);
	            scriptRunner.setSendFullScript(false);// true执行所有代码，否则按行识别代码
	            scriptRunner.setAutoCommit(true);
	            scriptRunner.runScript(reader1);
//	            scriptRunner.runScript(reader2);
	            scriptRunner.closeConnection();
	            conn.close();
			} catch (Exception e) {
				HXLogUtil.error(logger, "运行sql脚本错误{0}", e.getStackTrace());
			}
		}
	}

	
	/**
	 * 查询调用精英接口返回日志
	 */
	public List<CallJyLog> callJYLog(CallJyLog callJyLog) {
		List<CallJyLog> logList = callJyLogMapper.fuzzySelect(callJyLog);
		return logList;
	}
}
