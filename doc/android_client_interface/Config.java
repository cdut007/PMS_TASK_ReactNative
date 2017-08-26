package com.thirdpart.model;

import com.thirdpart.tasktrackerpms.ui.MyApplication;

import android.content.Context;
import android.content.SharedPreferences;

public class Config {

	
	public static final class  ReqHttpMethodPath{
		
		
		
		/** define the http server root url & port */
		public static  String HTTP_BASE_URL =MyApplication.getBaseUrl();// "http://122.225.41.42:8888/easycms-website";

		/** define the login url 
		 * <br>param:Login_ID,Password
		 * <br>-1001,id is not avaiable
		 * <br>-1002,password is wrong
		 * */
		public static final String REQUST_LOGIN_URL = HTTP_BASE_URL+"/hdxt/api/core/authenticate";
	
		/** define the rollingplan detail url 
		 * <br>param:{id}
		 * <br>-1001,id is not avaiable
		 * <br>-1002,unknow Error
		 * */
		public static final String REQUST_ROLLINGPLAN_DETAIL_URL = HTTP_BASE_URL+"/hdxt/api/baseservice/rollingplan/";
		
		/** define the rollingplan list url 
		 * <br>param:pagesize,pagenum
		 * <br>-1001,id is not avaiable
		 * <br>-1002,unknow Error
		 * */
		public static final String REQUST_ROLLINGPLAN_LIST_URL = HTTP_BASE_URL+"/hdxt/api/baseservice/rollingplan/";
		
		public static final String REQUST_ENDMAN_LIST_URL = HTTP_BASE_URL+"/hdxt/api/baseservice/construction/endman/";
		
		/** define the teamwork list url 
		 * <br>param:pagesize,pagenum
		 * <br>-1001,id is not avaiable
		 * <br>-1002,unknow Error
		 * */
		public static final String REQUST_TEAM_WROK_LIST_URL = HTTP_BASE_URL+"/hdxt/api/statistics/task";

		
		public static final String REQUST_TASK_STATUS_URL = HTTP_BASE_URL+"/hdxt/api/statistics/task/status";
		
		public static final String REQUST_ISSUE_STATISTIC_URL = HTTP_BASE_URL+"/hdxt/api/problem/statistic";

		public static final String REQUST_WITNESS_STATISTIC_URL = HTTP_BASE_URL+"/hdxt/api/baseservice/witness/statistic";

		
		public static final String REQUST_TASK_STATUS_LIST_URL =REQUST_TASK_STATUS_URL;
		
		
		public static final String REQUST_TEAM_GROUP_LIST_URL = HTTP_BASE_URL+"/hdxt/api/statistics/teamgroup/task";

		/** define the workstep detail url 
		 * <br>param:{id}
		 * <br>-1001,id is not avaiable
		 * <br>-1002,unknow Error
		 * */
		public static final String REQUST_WROKSTEP_DETAIL_URL = HTTP_BASE_URL+"/hdxt/api/baseservice/workstep/";
	
		/** define the workStep plan list url 
		 * <br>param:pagesize,pagenum
		 * <br>-1001,id is not avaiable
		 * <br>-1002,unknow Error
		 * */
		public static final String REQUST_WORK_STEP_LIST_URL = HTTP_BASE_URL+"/hdxt/api/baseservice/workstep/rollingplan/";

	
		public static final String REQUST_ALL_DEPARTMENT_URL = HTTP_BASE_URL+"/hdxt/api/core/department";
		public static final String REQUST_DEPARTMENT_USERS_URL = HTTP_BASE_URL+"/hdxt/api/core/user";

		public static final String REQUST_RETATIVE_DEPARTMENT_URL = HTTP_BASE_URL+"/hdxt/api/core/relationship";
		
		
		public static final String REQUST_RELATIONSHIP_DEPARTMENT_URL = HTTP_BASE_URL+"/hdxt/api/core/relationship";
		
		/** define the construction team list url 
		 * <br>param:pagesize,pagenum,condition,consteam
		 * <br>-1001,id is not avaiable
		 * <br>-1002,unknow Error
		 * */
		public static final String REQUST_CONSTRUCTION_TEAM_LIST_URL = HTTP_BASE_URL+"/hdxt/api/baseservice/construction/team";
	
		/** define theconstruction teams infos  url 
		 * <br>param:null
		 * <br>-1001,id is not avaiable
		 * <br>-1002,unknow Error
		 * */
		public static final String REQUST_CONSTRUCTION_TEAMS_INFOS_URL = HTTP_BASE_URL+"/hdxt/api/baseservice/construction/team/teams";

		/** define the distribute task to team  url 
		 * <br>POST param:teamId,Ids
		 * <br>-1001,id is not avaiable
		 * <br>-1002,unknow Error
		 * */
		public static final String REQUST_DISTRIBUTE_TASK_TO_TEAM_URL = REQUST_CONSTRUCTION_TEAM_LIST_URL;
		
		/** define the modify task to team  url 
		 * <br>PUT param:teamId,Ids
		 * <br>-1001,id is not avaiable
		 * <br>-1002,unknow Error
		 * */
		public static final String REQUST_MODIFY_TASK_TO_TEAM_URL = REQUST_CONSTRUCTION_TEAM_LIST_URL;
	

		/** define the remove task to team  url 
		 * <br>DELETE param:teamId,Ids
		 * <br>-1001,id is not avaiable
		 * <br>-1002,unknow Error
		 * */
		public static final String REQUST_REMOVE_TASK_TO_TEAM_URL = REQUST_CONSTRUCTION_TEAM_LIST_URL;
	
		/** define the get all hendmen  url 
		 * <br> param:loginUserId
		 * <br>-1001,id is not avaiable
		 * <br>-1002,unknow Error
		 * */
		public static final String REQUST_HEADMEN_URL = HTTP_BASE_URL+"/hdxt/api/baseservice/construction/endman/endmen";
		
		/** define the distribute rollingPlan task to hendman  url 
		 * <br> POST param:loginUserId,ids(rollingPlan id),endManId,startTime,endTime(format:2015-05-26)
		 * <br>-1001,id is not avaiable
		 * <br>-1002,unknow Error
		 * */
		public static final String REQUST_DISTRIBUTE_ROLLINGPLAN_TO_HEADMAN_URL = HTTP_BASE_URL+"/hdxt/api/baseservice/construction/endman";

		/** define the modify rollingPlan task to hendman  url 
		 * <br> PUT param:loginUserId,ids(rollingPlan id),endManId,startTime,endTime(format:2015-05-26)
		 * <br>-1001,id is not avaiable
		 * <br>-1002,unknow Error
		 * */
		public static final String REQUST_MODIFY_ROLLINGPLAN_TO_HEADMAN_URL = REQUST_DISTRIBUTE_ROLLINGPLAN_TO_HEADMAN_URL;

		/** define the remove rollingPlan task to hendman  url 
		 * <br> DELETE param:ids(rollingPlan id)
		 * <br>-1001,id is not avaiable
		 * <br>-1002,unknow Error
		 * */
		public static final String REQUST_REMOVE_ROLLINGPLAN_TO_HEADMAN_URL = REQUST_DISTRIBUTE_ROLLINGPLAN_TO_HEADMAN_URL;
	
		/** define the my task to finish for workstep  url 
		 * <br> POST param:loginUserId,id(rollingPlan id),operater(name),operatedate(format:2015-05-26 22:22:22)
		 * <br>-1001,id is not avaiable
		 * <br>-1002,unknow Error
		 * */
		public static final String REQUST_MY_TASK_TO_FINISH_BY_WORKSTEP_URL = HTTP_BASE_URL+"/hdxt/api/baseservice/construction/mytask/workstep";

		/** define the modify my task by workstep  url 
		 * <br> PUT param:loginUserId,id(rollingPlan id),operater(name),operatedate(format:2015-05-26 22:22:22)
		 * <br>-1001,id is not avaiable
		 * <br>-1002,unknow Error
		 * */
		public static final String REQUST_MY_TASK_MODIFY_BY_WORKSTEP_URL = REQUST_MY_TASK_TO_FINISH_BY_WORKSTEP_URL;
		
		
		public static final String REQUST_MY_TASK_WITNESS_LIST_URL = HTTP_BASE_URL+"/hdxt/api/baseservice/construction/mytask/witness";
		
		
		public static final String REQUST_TASK_WITNESS_BATCH_URL = HTTP_BASE_URL+"/hdxt/api/baseservice/construction/mytask/batch_witness";
		
		

		public static final String REQUST_WORKSTEP_BATCH_COMMIT_URL = HTTP_BASE_URL+"/hdxt/api/baseservice/construction/mytask/workstep/all";
		
		
		
		/** define the my task finish all to confirm  url 
		 * <br> POST param:loginUserId,id(rollingPlan id),welder(name),enddate(format:2015-05-26)
		 * <br>-1001,id is not avaiable
		 * <br>-1002,unknow Error
		 * */
		public static final String REQUST_MY_ROLLINGPLAN_FINISH_TO_CONFIRM_URL = HTTP_BASE_URL+"/hdxt/api/baseservice/construction/mytask/rollingplan";

		
		public static final String REQUST_MY_TASK_LIST_URL = HTTP_BASE_URL+"/hdxt/api/baseservice/construction/mytask";

		
		/** define the my task modify finish all to confirm  url 
		 * <br> PUT param:loginUserId,id(rollingPlan id),welder(name),enddate(format:2015-05-26)
		 * <br>-1001,id is not avaiable
		 * <br>-1002,unknow Error
		 * */
		public static final String REQUST_MY_ROLLINGPLAN_MODIFY_FINISH_TO_CONFIRM_URL = REQUST_MY_ROLLINGPLAN_FINISH_TO_CONFIRM_URL;

		/** define the witness list of workStep  url 
		 * <br>param:pagesize,pagenum,Id
		 * <br>-1001,id is not avaiable
		 * <br>-1002,unknow Error
		 * */
		public static final String REQUST_WITNESS_LIST_OF_WORKSTEP_URL = HTTP_BASE_URL+"/hdxt/api/baseservice/workstep/";

		/** define the witness result type  url 
		 * <br>{"code":"1000","message":"success","responseResult":{"3":"合格","1":"不合格"}}
		 * <br>-1001,id is not avaiable
		 * <br>-1002,unknow Error
		 * */
		public static final String REQUST_WITNESS_RESULT_TYPE_URL = HTTP_BASE_URL+"/hdxt/api/baseservice/witness/witnessresulttype";

		/** define the witness list of  distribute or not url 
		 * <br>param:loginUserId,pagesize,pagenum,condition (equal)
		 * <br>-1001,id is not avaiable
		 * <br>-1002,unknow Error
		 * */
		public static final String REQUST_WITNESS_LIST_OF_DISTRIBUTE_URL = HTTP_BASE_URL+"/hdxt/api/baseservice/witness";

		
		public static final String REQUST_MY_WITNESS_LIST_OF_DISTRIBUTE_URL = HTTP_BASE_URL+"/hdxt/api/baseservice/witness/myevent";

		
		/** define the witness  type (R,H,W) url 
		 * <br>-1001,id is not avaiable
		 * <br>-1002,unknow Error
		 * */
		public static final String REQUST_WITNESS_TYPE_LIST_URL = HTTP_BASE_URL+"/hdxt/api/baseservice/witness/witnessertype";

		/** define the witness  team list url 
		 * <br>-1001,id is not avaiable
		 * <br>-1002,unknow Error
		 * */
		public static final String REQUST_WITNESS_TEAM_LIST_URL = HTTP_BASE_URL+"/hdxt/api/baseservice/witness/team";

		/** define the witnesser  list url 
		 * <br>param:witness (ID)
		 * <br>-1001,id is not avaiable
		 * <br>-1002,unknow Error
		 * */
		public static final String REQUST_WITNESSER_LIST_URL = HTTP_BASE_URL+"/hdxt/api/baseservice/witness/witnesser";

		/** define the distributed witnesser  url 
		 * <br>POST param:loginUserId,witnessid
		 * <br>OPTION param:witnesseraqa,witnesseraqc1,
		 * witnesseraqc2,witnesserb,witnesserc,witnesserd
		 * <br>-1001,id is not avaiable
		 * <br>-1002,unknow Error
		 * */
		public static final String REQUST_DISTRIBUTED_WITNESSER_URL = REQUST_WITNESSER_LIST_URL;

		/** define the modify distributed witnesser  url 
		 * <br>PUT param:loginUserId,witnessid
		 * <br>OPTION param:witnesseraqa,witnesseraqc1,
		 * witnesseraqc2,witnesserb,witnesserc,witnesserd
		 * <br>-1001,id is not avaiable
		 * <br>-1002,unknow Error
		 * */
		public static final String REQUST_MODIFY_DISTRIBUTED_WITNESSER_URL = REQUST_WITNESSER_LIST_URL;

		
		/** define the witnesser write the witness result url 
		 * <br>POST param:noticeresult(1 ok ,3 bad),loginUserId,id
		 * <br>-1001,id is not avaiable
		 * <br>-1002,unknow Error
		 * */
		public static final String REQUST_WITNESSER_WIRTE_RESULT_URL = HTTP_BASE_URL+"/hdxt/api/baseservice/witness/witnesser/result";

		public static final String REQUST_MYEVENT_WIRTE_RESULT_URL = HTTP_BASE_URL+"/hdxt/api/baseservice/witness/myevent/result";

		/** define the modify witnesser write the witness result url 
		 * <br>PUT param:noticeresult(1 ok ,3 bad),loginUserId,id
		 * <br>-1001,id is not avaiable
		 * <br>-1002,unknow Error
		 * */
		public static final String REQUST_MODIFY_WITNESSER_WIRTE_RESULT_URL = REQUST_WITNESSER_WIRTE_RESULT_URL;

		/** define the my witness list  url 
		 * <br>param:loginUserId,pagesize,pagenum
		 * <br>-1001,id is not avaiable
		 * <br>-1002,unknow Error
		 * */
		public static final String REQUST_MY_WITNESS_LIST_URL = HTTP_BASE_URL+"/hdxt/api/baseservice/witness/myevent";

		/** define the create issue  url 
		 * <br>POST param:userid,workstepid,workstepno,
		 * stepname,questionname,describe,solverid,concernman
		 * <br>-1001,id is not avaiable
		 * <br>-1002,unknow Error
		 * */
		public static final String REQUST_CREATE_ISSUE_URL = HTTP_BASE_URL+"/hdxt/api/problem/add";

		/** define the upload issue files url 
		 * <br>POST param:problemId,
		 * <br>-1001,id is not avaiable
		 * <br>-1002,unknow Error
		 * */
		public static final String REQUST_UPLOAD_ISSUE_FILES_URL = HTTP_BASE_URL+"/hdxt/api/problem/upload";

		/** define the handl issue  url 
		 * <br>POST param:userid,problemid,autoid,solvedman,
		 * isSolve,solverid
		 * <br>-1001,id is not avaiable
		 * <br>-1002,unknow Error
		 * */
		public static final String REQUST_HANDLE_ISSUE_URL = HTTP_BASE_URL+"/hdxt/api/problem/handle";

		/** define the confirm issue  url 
		 * <br>POST param:userid,id,iswork
		 * <br>-1001,id is not avaiable
		 * <br>-1002,unknow Error
		 * */
		public static final String REQUST_CONFIRM_ISSUE_URL = HTTP_BASE_URL+"/hdxt/api/problem/confirm";

		
		/** define the get issue status url 
		 * <br> param:userid,status
		 * <br>-1001,id is not avaiable
		 * <br>-1002,unknow Error
		 * */
		public static final String REQUST_STATUS_ISSUE_URL = HTTP_BASE_URL+"/hdxt/api/problem";

		public static final String REQUST_PLAN_ISSUE_LIST_URL = HTTP_BASE_URL+"/hdxt/api/problem/rollingplan";

		
		/** define the get issue status url 
		 * <br> param:id
		 * <br>-1001,id is not avaiable
		 * <br>-1002,unknow Error
		 * */
		public static final String REQUST_ISSUE_DETAIL_URL = HTTP_BASE_URL+"/hdxt/api/problem/detail";

	}	

}
