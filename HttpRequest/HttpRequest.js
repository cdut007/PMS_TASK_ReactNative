import { AsyncStorage } from 'react-native';

const apiAddr =  'http://47.88.139.113:8080/easycms_website' //http://106.37.241.216:5555/easycms-website
var httpToken = ''
var Global = require('../common/globals');

module.exports = {
get(apiName, body,successCallback, failCallback)
{
    // if(!httpToken.length)
    // {
    //     httpToken = Global.token;
    //
    //     AsyncStorage.getItem('k_http_token',function(errs,result)
    //     {
    //         if (!errs)
    //         {
    //             httpToken = result
    //             console.log('httpToken = '+httpToken)
    //         }
    //         else
    //         {
    //             console.log('get http token error:' + errs)
    //         }
    //     });
    //
    //
    //
    // }else{
    //
    // }

    var url = apiAddr + apiName
    var param = ""

    if (Global.UserInfo)
    {
        body.loginId = Global.UserInfo.id
    }


    for(var element in body){
        param += element + "=" + body[element] + "&";
    }

    url =  url+"?"+param;

    console.log('Get requesr:' + url)
    fetch(url, {
        method: 'GET',})
      .then((response) => response.text())
      .then((responseText) => {
        console.log("get request response:"+responseText);
        var response = JSON.parse(responseText);
        if (response.code == 1000) {
            successCallback(response);
        }else{
            if (response.message) {
                failCallback(response.message)
            }else{
                failCallback(response.responseText)
            }

        }

      })
      .catch(function(err){
        failCallback(err);
      });

  },

post(apiName, body,successCallback, failCallback)
  {
    // if(!httpToken.length)
    // {
    //     httpToken = Global.token;

    //     AsyncStorage.getItem('k_http_token',function(errs,result)
    //     {
    //         if (!errs)
    //         {
    //             httpToken = result
    //             console.log('httpToken = '+httpToken)
    //         }
    //         else
    //         {
    //             console.log('get http token error:' + errs)
    //         }
    //     });
    // }

    var logind = '';
    if (Global.UserInfo)
    {
        logind = Global.UserInfo.id;
        body.append('loginId', Global.UserInfo.id)
    }

    var url = apiAddr + apiName +"?loginId="+logind
     try {
         console.log('Post requesr:' + url +":[param body]="+JSON.stringify(body))
     } catch (e) {

     } finally {

     }

    fetch(url, {
        method: 'POST',
        body: body})
      .then((response) => response.text())
      .then((responseText) => {
        console.log(responseText);
        var response = JSON.parse(responseText);
        if (response.code == 1000) {
            successCallback(response);
        }else{
            if (response.message) {
                failCallback(response.message)
            }else{
                failCallback(response.responseText)
            }
        }

      })
      .catch(function(err){
        failCallback(err);
      });
  }
}
