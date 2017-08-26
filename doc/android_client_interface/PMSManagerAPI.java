package com.thirdpart.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.R.integer;
import android.content.Context;
import android.net.ConnectivityManager;
import android.text.TextUtils;

import com.jameschen.comm.utils.MyHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.thirdpart.model.Config.ReqHttpMethodPath;
import com.thirdpart.model.entity.IssuePhoto;
import com.thirdpart.model.entity.IssueResult;
import com.thirdpart.tasktrackerpms.ui.HomeFragment.TaskItem;

public class PMSManagerAPI {

	private static PMSManagerAPI managerAPI;

	private Context context;

	private LogInController mLogInController;

	public Map<String, String> getPublicParamRequstMap() {
		Map<String, String> headers = new HashMap<String, String>();

		return headers;
	}

	private RequestParams getCommonPageParams(boolean login, String pageSize, String pageNum) {
		// TODO Auto-generated method stub
		RequestParams requestParams = getPublicParams(login);
		requestParams.put("pagesize", pageSize);
		requestParams.put("pagenum", pageNum);
		return requestParams;
	}
	
	private RequestParams getCommonPageParams(String pageSize, String pageNum) {
		// TODO Auto-generated method stub
		
		return getCommonPageParams(true, pageSize, pageNum);
	}

	private String keyword="";
	
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	private RequestParams getPublicParams(boolean loginId) {
		// TODO Auto-generated method stub
		RequestParams requestParams = new RequestParams(getPublicParamRequstMap());
		if (loginId) {
			requestParams.put("loginId", getLogId());
		}
		requestParams.put("keyword", keyword);
		return requestParams;
	}

	private RequestParams getPublicParams() {
		// TODO Auto-generated method stub

		return getPublicParams(true);
	}

	private PMSManagerAPI(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	public static PMSManagerAPI getInstance(Context context) {
		// TODO Auto-generated method stub
		synchronized (PMSManagerAPI.class) {
			if (managerAPI == null) {
				managerAPI = new PMSManagerAPI(context);
			}
		}
		return managerAPI;
	}

	
	private String  getLogId() {
		// TODO Auto-generated method stub
		return getLogInController().getInfo().getId();
	}
	
	private LogInController getLogInController() {
		// TODO Auto-generated method stub
    	  mLogInController = LogInController.getInstance(context);
      
      return mLogInController;
	}
	/**
	 * @param loginId
	 * @param password
	 * @param responseHandler
	 *            userInfo
	 */
	public void login(String loginId, String password,
			AsyncHttpResponseHandler responseHandler) {
		RequestParams params = getPublicParams(false);
		params.put("LoginId", loginId);
		params.put("password", password);
		params.put("uuid", LogInController.getUUID(context));
		MyHttpClient.get(ReqHttpMethodPath.REQUST_LOGIN_URL, params,
				responseHandler);

	}

	/**
	 * @param loginId
	 * @param password
	 * @param responseHandler
	 *            ConstructionTeamList
	 */
	public void teamList(String pagesize, String pagenum, String condition,
			String consteam, AsyncHttpResponseHandler responseHandler) {
		RequestParams params = getCommonPageParams(pagesize, pagenum);
		params.put("condition", condition);
		params.put("consteam", consteam);
		MyHttpClient.get(ReqHttpMethodPath.REQUST_CONSTRUCTION_TEAM_LIST_URL,
				params, responseHandler);

	}
	public void myTaskList(String pagesize, String pagenum, String condition,AsyncHttpResponseHandler responseHandler) {
		RequestParams params = getCommonPageParams(pagesize, pagenum);
		params.put("condition", condition);
		MyHttpClient.get(ReqHttpMethodPath.REQUST_MY_TASK_LIST_URL,
				params, responseHandler);

	}

	/**
	 *
	 * @param responseHandler
	 */
	public void teamGroupList(
			AsyncHttpResponseHandler responseHandler) {
		RequestParams params = getPublicParams(true);
		MyHttpClient.get(ReqHttpMethodPath.REQUST_TEAM_GROUP_LIST_URL, params,
				responseHandler);

	}
	/**
	 * @param pagesize
	 * @param pagenum
	 * @param responseHandler
	 */
	public void teamWorkList(
			AsyncHttpResponseHandler responseHandler) {
		RequestParams params = getPublicParams();
		MyHttpClient.get(ReqHttpMethodPath.REQUST_TEAM_WROK_LIST_URL, params,
				responseHandler);

	}
	
	/**
	 * @param pagesize
	 * @param pagenum
	 * @param responseHandler
	 */
	public void endmanList(String pagesize, String pagenum,String condition,
			AsyncHttpResponseHandler responseHandler) {
		RequestParams params = getCommonPageParams(pagesize, pagenum);
		params.put("condition", condition);
		MyHttpClient.get(ReqHttpMethodPath.REQUST_ENDMAN_LIST_URL, params,
				responseHandler);

	}

	
	public void taskList(TaskItem taskItem,String pagesize, String pagenum,
			AsyncHttpResponseHandler responseHandler) {
		RequestParams params = getCommonPageParams(pagesize, pagenum);
		params.put("category", taskItem.category);
		params.put("taskDate", taskItem.taskDate);
		params.put("type", taskItem.type);
		params.put("status", taskItem.status);
		MyHttpClient.get(ReqHttpMethodPath.REQUST_ROLLINGPLAN_LIST_URL, params,
				responseHandler);

	}
	/**
	 * @param witnessId
	 * @param responseHandler
	 *            WitnesserList
	 */
	public void witnesserList(String witnessId,
			AsyncHttpResponseHandler responseHandler) {
		RequestParams params = getPublicParams();
		params.put("witness", witnessId);
		MyHttpClient.get(ReqHttpMethodPath.REQUST_WITNESSER_LIST_URL, params,
				responseHandler);
	}

	/**
	 * @param loginid
	 * @param qustionId
	 * @param iswork
	 * @param responseHandler
	 */
	public void confirmIssue(String qustionId, String iswork,
			AsyncHttpResponseHandler responseHandler) {
		RequestParams params = getPublicParams(true);
		params.put("id", qustionId);
		params.put("isWork", iswork);
		MyHttpClient.post(ReqHttpMethodPath.REQUST_CONFIRM_ISSUE_URL, params,
				responseHandler);
	}

	/**
	 * @param responseHandler
	 *            List<Team>[team(id,name)]
	 */
	public void teamInfos(AsyncHttpResponseHandler responseHandler) {
		RequestParams params = getPublicParams();
		MyHttpClient.get(ReqHttpMethodPath.REQUST_CONSTRUCTION_TEAMS_INFOS_URL,
				params, responseHandler);
	}

	/**
	 * @param issue
	 * @param mFiles 
	 * @param responseHandler
	 */
	public void createIssue(IssueResult issue,
			List<File> mFiles, AsyncHttpResponseHandler responseHandler) {
		RequestParams params = getPublicParams(true);

		params.put("workstepid", issue.getWorstepid());
		params.put("rollingPlanId", issue.rollingPlanId);
		
		params.put("stepno", issue.getStepno());
		params.put("stepname", issue.getStepname());
		params.put("questionname", issue.getQuestionname());
		params.put("describe", issue.getDescribe());
		params.put("solverid", issue.getSolverid());
		//params.put("concernman", issue.getConcerman());
		for (int j = 0; j < mFiles.size(); j++) {
			try {
				params.put("file"+j, mFiles.get(j));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	
		MyHttpClient.post(ReqHttpMethodPath.REQUST_CREATE_ISSUE_URL, params,
				responseHandler);
	}

	

	/**
	 * @param loginUserId
	 * @param rollingPlanIds
	 * @param endManId
	 * @param startTime
	 * @param endTime
	 *            (format:2015-05-26)
	 * @param responseHandler
	 */
	HashSet<String> getHashSet(List<String> mList){
		HashSet<String> mHashSet = new HashSet<String>();
		for (String string : mList) {
			mHashSet.add(string);
		}
		return mHashSet;
	}
	
	public void deliveryPlanToHeadMan(
			List<String> rollingPlanIds, String endManId, String startTime,
			String endTime, AsyncHttpResponseHandler responseHandler) {
		RequestParams params = getPublicParams(true);
		
		params.put("ids", getHashSet(rollingPlanIds));
		params.put("endManId", endManId);
		params.put("startTime", startTime);
		params.put("endTime", endTime);

		MyHttpClient.post(
				ReqHttpMethodPath.REQUST_DISTRIBUTE_ROLLINGPLAN_TO_HEADMAN_URL,
				params, responseHandler);
	}
	
	public void modifyPlanToHeadMan(
			List<String> rollingPlanIds, String endManId, String startTime,
			String endTime, AsyncHttpResponseHandler responseHandler) {
		RequestParams params = getPublicParams(true);
		
		params.put("ids", getHashSet(rollingPlanIds));
		params.put("endManId", endManId);
		params.put("startTime", startTime);
		params.put("endTime", endTime);

		MyHttpClient.put(
				ReqHttpMethodPath.REQUST_MODIFY_ROLLINGPLAN_TO_HEADMAN_URL,
				params, responseHandler);
	}

	/**
	 * @param teamId
	 * @param rollingPlanIds
	 * @param responseHandler
	 */
	public void deliveryPlanToTeam(String teamId,String finishDate, List<String> rollingPlanIds,
			AsyncHttpResponseHandler responseHandler) {

		RequestParams params = getPublicParams();
		params.put("teamId", teamId);
		params.put("ids", getHashSet(rollingPlanIds));
		params.put("planfinishdate", finishDate);
		MyHttpClient.post(ReqHttpMethodPath.REQUST_DISTRIBUTE_TASK_TO_TEAM_URL,
				params, responseHandler);
	}
	
	public void modifyPlanToTeam(String teamId, List<String> rollingPlanIds,
			AsyncHttpResponseHandler responseHandler) {

		RequestParams params = getPublicParams();
		params.put("teamId", teamId);
		params.put("ids", getHashSet(rollingPlanIds));

		MyHttpClient.put(ReqHttpMethodPath.REQUST_MODIFY_TASK_TO_TEAM_URL,
				params, responseHandler);
	}

	/**
	 * Method : POST param:loginUserId,witnessid OPTION
	 * param:witnesseraqa,witnesseraqc1,
	 * witnesseraqc2,witnesserb,witnesserc,witnesserd
	 * 
	 * @param loginUserId
	 * @param witnessid
	 * @param witnesseraqa
	 * @param witnesseraqc2
	 * @param witnesseraqc1
	 * @param witnesserb
	 * @param witnesserc
	 * @param witnesserd
	 * 
	 * @param responseHandler
	 */
	public void deliveryWitness( String witnessid,
			String witnesseraqa, String witnesseraqc2, String witnesseraqc1,
			String witnesserb, String witnesserc, String witnesserd,
			AsyncHttpResponseHandler responseHandler) {

		RequestParams params = getPublicParams(true);
		params.put("witnessid", witnessid);

		if (witnesseraqa != null) {
			params.put("witnesseraqa", witnesseraqa);
		}
		if (witnesseraqc1 != null) {
			params.put("witnesseraqc1", witnesseraqc1);
		}
		if (witnesseraqc2 != null) {
			params.put("witnesseraqc2", witnesseraqc2);
		}
		if (witnesserb != null) {
			params.put("witnesserb", witnesserb);
		}
		if (witnesserc != null) {
			params.put("witnesserc", witnesserc);
		}
		if (witnesserd != null) {
			params.put("witnesserd", witnesserd);
		}
		MyHttpClient.post(ReqHttpMethodPath.REQUST_DISTRIBUTED_WITNESSER_URL,
				params, responseHandler);
	}
	
	public static String combineWorkIdTime(String id,String formatTime){
		
		return id+"|"+formatTime;
	}
	
	
    public void createPlanWorkStepBatchCommit(String operatre,String batchDate,String operatedesc,int qcassign,String qcman,List<String>planIds
    		,AsyncHttpResponseHandler responseHandler){
    	RequestParams params = getPublicParams(true);
    	params.put("operatedesc", operatedesc);
    	params.put("operater", operatre);
    	params.put("operatedate", batchDate);
    	if (qcassign>=0) {
			params.put("qcsign", qcassign+"");
			params.put("qcman", qcman);
				
		}
    	params.put("backFillWorkStepId", planIds);
    	MyHttpClient.post(
				ReqHttpMethodPath.REQUST_WORKSTEP_BATCH_COMMIT_URL,
				params, responseHandler);
    	
    }
	
	public void createWitnessTaskBatch(String rollingplanId,String witnessW,
			String witnessH,String witnessR,
			String witness,String witnessdes,
			String witnesseaddress,List<String> witnessDatas, 
			AsyncHttpResponseHandler responseHandler) {
		
		RequestParams params = getPublicParams(true);
		
		//params.put("witness", witness);
		if (witnessW!=null) {
			params.put("witnessW", witnessW);
		}
		if (witnessH!=null) {
			params.put("witnessH", witnessH);
		}
		
		if (witnessR!=null) {
			params.put("witnessR", witnessR);
		}
		
		params.put("rollingplanid", rollingplanId);
		params.put("witnessaddress", witnesseaddress);
		
		for (Iterator iterator = witnessDatas.iterator(); iterator.hasNext();) {
			String  data =  (String) iterator
					.next();
			String keyValue[] = data.split("\\|");
			if (keyValue!=null &&keyValue.length>=2) {
				params.put("witnessdate_"+keyValue[0], keyValue[1]);	
			}
			
			
		}
		
		
		
		if (witnessdes != null) {
			params.put("witnessdes", witnessdes);
		}
		
		
		MyHttpClient.post(
				ReqHttpMethodPath.REQUST_TASK_WITNESS_BATCH_URL,
				params, responseHandler);
	}

	
//	id				工序步骤ID
//	witness				见证组组长ID
//	witnessdes		N		见证描述
//	witnessaddress				见证地点
//	witnessdate				见证时间
//	operater				完成者
//	operatedate				完成时间（格式2015-05-24 22:22:45）
//	operatedesc		N		完成信息描述
	public void createWitness(String workStepId,String witnessW,String witnessH,String witnessR,
			String witness,String witnessdes,
			String witnesseaddress, String witnessdate, String operater,
			String operatedate, String operatedesc, int qcsign,String qcman,
			AsyncHttpResponseHandler responseHandler) {
		
		RequestParams params = getPublicParams(true);
		
		//params.put("witness", witness);
		if (witnessH!=null) {
			params.put("witnessH", witnessH);
		}
		if (witnessW!=null) {
			params.put("witnessW", witnessW);
		}
		if (witnessR!=null) {
			params.put("witnessR", witnessR);
		}
		
		params.put("id", workStepId);
		params.put("witnessaddress", witnesseaddress);
		params.put("operater", operater);
		params.put("operatedate", operatedate);
		params.put("witnessdate", witnessdate);
		if (qcsign>=0) {
			params.put("qcsign", qcsign+"");
			params.put("qcman", qcman);
				
		}
		if (witnessdes != null) {
			params.put("witnessdes", witnessdes);
		}
		if (operatedesc != null) {
			params.put("operatedesc", operatedesc);
		}
		
		
		MyHttpClient.post(
				ReqHttpMethodPath.REQUST_MY_TASK_WITNESS_LIST_URL,
				params, responseHandler);
	}

	/**
	 * @param loginUserId
	 * @param witnessid
	 * @param witnesseraqa
	 * @param witnesseraqc2
	 * @param witnesseraqc1
	 * @param witnesserb
	 * @param witnesserc
	 * @param witnesserd
	 * @param responseHandler
	 */
	public void modifyWitness(String witnessid,
			String witnesseraqa, String witnesseraqc2, String witnesseraqc1,
			String witnesserb, String witnesserc, String witnesserd,
			String witnessdate,AsyncHttpResponseHandler responseHandler) {
		RequestParams params = getPublicParams(true);
		
		params.put("witnessid", witnessid);

		if (witnesseraqa != null) {
			params.put("witnesseraqa", witnesseraqa);
		}
		if (witnesseraqc1 != null) {
			params.put("witnesseraqc1", witnesseraqc1);
		}
		if (witnesseraqc2 != null) {
			params.put("witnesseraqc2", witnesseraqc2);
		}
		if (witnesserb != null) {
			params.put("witnesserb", witnesserb);
		}
		if (witnesserc != null) {
			params.put("witnesserc", witnesserc);
		}
		if (witnesserd != null) {
			params.put("witnesserd", witnesserd);
		}
		if (witnessdate!=null) {
			params.put("witnessdate", witnessdate);
		}
		MyHttpClient.post(
				ReqHttpMethodPath.REQUST_MODIFY_DISTRIBUTED_WITNESSER_URL,
				params, responseHandler);
	}

	/**
	 * @param loginid
	 * @param problemid
	 * @param autoid
	 * @param solvedman
	 * @param isSolve
	 * @param solverid
	 * @param solveramethod 
	 * @param responseHandler
	 */
	public void handleIssue(String problemid,String solvemethod, String autoid,
			String solvedman, String isSolve, String solverid,
			 AsyncHttpResponseHandler responseHandler) {
		RequestParams params = getPublicParams(true);
		params.put("id", problemid);
		params.put("solvemethod", solvemethod);
		params.put("autoid", autoid);
		params.put("solvedman", solvedman);
		params.put("isSolve", isSolve);
		params.put("solverid", solverid);
		MyHttpClient.post(ReqHttpMethodPath.REQUST_HANDLE_ISSUE_URL, params,
				responseHandler);
	}

	/**
	 * @param loginUserId
	 * @param responseHandler
	 */
	public void headmens(
			AsyncHttpResponseHandler responseHandler) {
		RequestParams params = getPublicParams(true);
		MyHttpClient.get(ReqHttpMethodPath.REQUST_HEADMEN_URL, params,
				responseHandler);
	}

	/**
	 * @param issueId
	 * @param responseHandler
	 */
	public void IssueDetail(String issueId,
			AsyncHttpResponseHandler responseHandler) {
		RequestParams params = getPublicParams();
		params.put("id", issueId);
		MyHttpClient.get(ReqHttpMethodPath.REQUST_ISSUE_DETAIL_URL, params,
				responseHandler);
	}

	/**PUT param:noticeresult(1 ok ,3 bad),loginUserId,id 
	 * @param responseHandler
	 * @param noticeresult 
	 * @param loginUserId 
	 * @param id 
	 */
	public void modifyWitnesserWriteResult(String noticeresult, String id,AsyncHttpResponseHandler responseHandler) {
		RequestParams params = getPublicParams(true);
		params.put("noticeresult", noticeresult);
		params.put("id", id);
		MyHttpClient.put(ReqHttpMethodPath.REQUST_MODIFY_WITNESSER_WIRTE_RESULT_URL,
				params, responseHandler);
	}
	
	
	/**
	 * POST param:loginUserId,id(rollingPlan id),welder(name),enddate(format:2015-05-26) 
	 * @param loginUserId
	 * @param rollingPlanid
	 * @param welder
	 * @param enddate
	 * @param responseHandler
	 */
	public void confirmMyPlanFinish( String rollingPlanid, String welder,String enddate,String qcsign,String qcman,AsyncHttpResponseHandler responseHandler) {
		RequestParams params = getPublicParams(true);
		params.put("id", rollingPlanid);
		params.put("welder", welder);
		params.put("enddate", enddate);
		params.put("qcman", qcman);
		params.put("qcsign", qcsign);
		MyHttpClient.post(ReqHttpMethodPath.REQUST_MY_ROLLINGPLAN_FINISH_TO_CONFIRM_URL,
				params, responseHandler);
	}
	
	public void modifyMyPlanFinishResult(String rollingPlanid, String welder,String enddate,AsyncHttpResponseHandler responseHandler) {
		RequestParams params = getPublicParams(true);
		params.put("id", rollingPlanid);
		params.put("welder", welder);
		params.put("enddate", enddate);
		MyHttpClient.put(ReqHttpMethodPath.REQUST_MY_ROLLINGPLAN_MODIFY_FINISH_TO_CONFIRM_URL,
				params, responseHandler);
	}

	/**PUT param:loginUserId,id(rollingPlan id),operater(name),operatedate(format:2015-05-26 22:22:22) 
	 * @param loginUserId
	 * @param rollingPlanid
	 * @param operater
	 * @param operatedate
	 * @param responseHandler
	 */
	public void modifyMyTaskByWorkStep( String rollingPlanid, String operater,String operatedate,AsyncHttpResponseHandler responseHandler) {
		RequestParams params = getPublicParams(true);
		params.put("id", rollingPlanid);
		params.put("operater", operater);
		params.put("operatedate", operatedate);
		MyHttpClient.put(ReqHttpMethodPath.REQUST_MY_TASK_MODIFY_BY_WORKSTEP_URL,
				params, responseHandler);
	}
	
	public void finishMyTaskByWorkStep( String rollingPlanid, String operater,String operatedate,AsyncHttpResponseHandler responseHandler) {
		RequestParams params = getPublicParams(true);
		params.put("id", rollingPlanid);
		params.put("operater", operater);
		params.put("operatedate", operatedate);
		MyHttpClient.post(ReqHttpMethodPath.REQUST_MY_TASK_TO_FINISH_BY_WORKSTEP_URL,
				params, responseHandler);
	}
	
	public void myWitnessList(String pagesize, String pagenum,AsyncHttpResponseHandler responseHandler) {
		RequestParams params = getCommonPageParams(true,pagesize, pagenum);
	
		MyHttpClient.post(ReqHttpMethodPath.REQUST_MY_WITNESS_LIST_URL,
				params, responseHandler);
	}
	
	public void removePlansToHeadman(List<String> rollingPlanIds, AsyncHttpResponseHandler responseHandler) {
		RequestParams params = getPublicParams();
		params.put("ids", getHashSet(rollingPlanIds));
		MyHttpClient.delete(ReqHttpMethodPath.REQUST_REMOVE_ROLLINGPLAN_TO_HEADMAN_URL,
				params, responseHandler);
	}
	
	public void removeTaskToTeam(String teamId,List<String> taskIds, AsyncHttpResponseHandler responseHandler) {
		RequestParams params = getPublicParams();
		params.put("teamId", teamId);
		params.put("ids", getHashSet(taskIds));
		MyHttpClient.delete(ReqHttpMethodPath.REQUST_REMOVE_TASK_TO_TEAM_URL,
				params, responseHandler);
	}
	
	public void planDetail(String planid, AsyncHttpResponseHandler responseHandler) {
		RequestParams params = getPublicParams();
		MyHttpClient.get(ReqHttpMethodPath.REQUST_ROLLINGPLAN_DETAIL_URL+planid,
				params, responseHandler);
	}
	
	public void IssueStatus(String status,String pagesize,String pagenum, AsyncHttpResponseHandler responseHandler) {
		RequestParams params = getCommonPageParams(true,pagesize, pagenum);
		params.put("status", status);
		MyHttpClient.get(ReqHttpMethodPath.REQUST_STATUS_ISSUE_URL,
				params, responseHandler);
	}
	
	public void IssuePlanList(String planId,String pagesize,String pagenum, AsyncHttpResponseHandler responseHandler) {
		RequestParams params = getCommonPageParams(true,pagesize, pagenum);
		params.put("rollingPlanId", planId);
		MyHttpClient.get(ReqHttpMethodPath.REQUST_PLAN_ISSUE_LIST_URL,
				params, responseHandler);
	}
	//param:loginUserId,pagesize,pagenum,condition (equal) 
	
	/**
	 * @param loginUserId
	 * @param pageSize
	 * @param pageNum
	 * @param condition
	 * @param responseHandler
	 */
	public void myTaskWitnessList(
			String pageSize,String pageNum,String condition,
			AsyncHttpResponseHandler responseHandler) {
		RequestParams params = getCommonPageParams(true,pageSize, pageNum);
		params.put("condition", condition);
		MyHttpClient.get(ReqHttpMethodPath.REQUST_MY_TASK_WITNESS_LIST_URL,
				params, responseHandler);
	}
	
	
	public void receiveMyWitnessList(
			String pageSize,String pageNum,
			AsyncHttpResponseHandler responseHandler, String condition,String categoryWitnessTag) {
		RequestParams params = getCommonPageParams(true,pageSize, pageNum);
		if (condition!=null) {
			params.put("condition", condition);
		}
		
		if (categoryWitnessTag!=null) {
			params.put("categoryFilter", categoryWitnessTag);
		}
		
		MyHttpClient.get(ReqHttpMethodPath.REQUST_MY_WITNESS_LIST_OF_DISTRIBUTE_URL,
				params, responseHandler);
	}
	
	public void receiveWitnessList(
			String pageSize,String pageNum,String condition,String categoryWitnessTag,
			AsyncHttpResponseHandler responseHandler) {
		RequestParams params = getCommonPageParams(true,pageSize, pageNum);
		params.put("condition", condition);
		if (categoryWitnessTag!=null) {
			params.put("categoryFilter", categoryWitnessTag);
		}
		MyHttpClient.get(ReqHttpMethodPath.REQUST_WITNESS_LIST_OF_DISTRIBUTE_URL,
				params, responseHandler);
	}
	
	public void workStepWitnessList(String id, 
			String pageSize,String pageNum, AsyncHttpResponseHandler responseHandler) {
		RequestParams params = getCommonPageParams(pageSize, pageNum);
		params.put("Id", id);
		MyHttpClient.get(ReqHttpMethodPath.REQUST_WITNESS_LIST_OF_WORKSTEP_URL,
				params, responseHandler);
	}
	
	public void getWitnessResultType( AsyncHttpResponseHandler responseHandler) {
		RequestParams params = getPublicParams();
		MyHttpClient.get(ReqHttpMethodPath.REQUST_WITNESS_RESULT_TYPE_URL,
				params, responseHandler);
	}
	
	public void witnessTeamList( AsyncHttpResponseHandler responseHandler) {
		RequestParams params = getPublicParams();
		MyHttpClient.get(ReqHttpMethodPath.REQUST_WITNESS_TEAM_LIST_URL,
				params, responseHandler);
	}
	
	public void getWitnessType( AsyncHttpResponseHandler responseHandler) {
		RequestParams params = getPublicParams();
		MyHttpClient.get(ReqHttpMethodPath.REQUST_WITNESS_TYPE_LIST_URL,
				params, responseHandler);
	}
	
	
	public void getDepartmentUser( String departmentId,AsyncHttpResponseHandler responseHandler) {
		RequestParams params = getPublicParams();
		params.put("departmentId", departmentId);
		MyHttpClient.get(ReqHttpMethodPath.REQUST_DEPARTMENT_USERS_URL,
				params, responseHandler);
	}
	
	public void getSovers( AsyncHttpResponseHandler responseHandler) {
		RequestParams params = getPublicParams();
			
		MyHttpClient.get(ReqHttpMethodPath.HTTP_BASE_URL+"/hdxt/api/core/solvers",
				params, responseHandler);
	}
	
	public void getDepartment( AsyncHttpResponseHandler responseHandler) {
		RequestParams params = getPublicParams();
		//set to the all department.
		MyHttpClient.get(ReqHttpMethodPath.REQUST_ALL_DEPARTMENT_URL,
				params, responseHandler);
//		MyHttpClient.get(ReqHttpMethodPath.REQUST_RETATIVE_DEPARTMENT_URL,
//				params, responseHandler);
	}
	
	public void getWorkStepList(String id,String pagesize,String pagenum, AsyncHttpResponseHandler responseHandler) {
		RequestParams params = getCommonPageParams(pagesize, pagenum);
		
		MyHttpClient.get(ReqHttpMethodPath.REQUST_WORK_STEP_LIST_URL+id,
				params, responseHandler);
	}
	
	
	//noticeresult(1 ok ,3 bad),loginUserId,id 
		public void wirteMyeventWitnessResult(CharSequence content, String noticeresult,String id, AsyncHttpResponseHandler responseHandler) {
			RequestParams params = getPublicParams(true);
			params.put("isok", noticeresult);
			if (!TextUtils.isEmpty(content)) {
				params.put("noticeresultdesc", content.toString());	
			}
			//
			params.put("id", id);
			MyHttpClient.post(ReqHttpMethodPath.REQUST_MYEVENT_WIRTE_RESULT_URL,
					params, responseHandler);
		}
	
	//noticeresult(1 ok ,3 bad),loginUserId,id 
	public void wirteWitnessResult(CharSequence content, String noticeresult,String id, AsyncHttpResponseHandler responseHandler) {
		RequestParams params = getPublicParams(true);
		params.put("noticeresult", noticeresult);
		if (!TextUtils.isEmpty(content)) {
			params.put("noticeresultdesc", content.toString());	
		}
		//
		params.put("id", id);
		MyHttpClient.post(ReqHttpMethodPath.REQUST_WITNESSER_WIRTE_RESULT_URL,
				params, responseHandler);
	}
	
	public void getWorkStepDetail(String id, AsyncHttpResponseHandler responseHandler) {
		RequestParams params = getPublicParams();
		MyHttpClient.get(ReqHttpMethodPath.REQUST_WROKSTEP_DETAIL_URL+id,
				params, responseHandler);
	}
	
	
	public void getWitnessCategoryCount(AsyncHttpResponseHandler responseHandler) {
		
		RequestParams params = getPublicParams(true);
	
		MyHttpClient.get(ReqHttpMethodPath.REQUST_WITNESS_STATISTIC_URL,
				params, responseHandler);
	}
	
	
	public void getIssueCategoryCount(AsyncHttpResponseHandler responseHandler) {
	
		RequestParams params = getPublicParams(true);
	
		MyHttpClient.get(ReqHttpMethodPath.REQUST_ISSUE_STATISTIC_URL,
				params, responseHandler);
	}
	
/**	taskDate		R		查询的时间
	格式：yyyy-MM-dd
	category		R		查询的类型
	允许的值：dateYear, dateWeek, dateMonth, dateAfter, dateCurrent, dateBefore*/
	/**
	 * @param category
	 * @param responseHandler
	 */
	public void getTaskFinishCountStatus(String category, AsyncHttpResponseHandler responseHandler) {
		RequestParams params = getPublicParams(true);
		params.put("taskDate", getdateformat(System.currentTimeMillis()));
		params.put("category", category);
		MyHttpClient.get(ReqHttpMethodPath.REQUST_TASK_STATUS_URL,
				params, responseHandler);
	}
	
	public static String getdateTimeformat(long times) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(times);
		return sdf.format(date);
	}
	
	public static String getdateformat(long times) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(times);
		return sdf.format(date);
	}

	public static ArrayList<String> getPhotosUrls(List<IssuePhoto> file) {
		// TODO Auto-generated method stub
		ArrayList<String> pArrayList = new ArrayList<String>();
		
		for (IssuePhoto issuePhoto : file) {
			pArrayList.add(Config.ReqHttpMethodPath.HTTP_BASE_URL+"/problem_files/"+issuePhoto.getPath());
		}
		return pArrayList;
	}
	
	
	
}
