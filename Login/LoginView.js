import React, { Component } from 'react';
import HttpRequest from '../HttpRequest/HttpRequest'
import {
    TextInput,
    StyleSheet,
    View,
    TouchableOpacity,
    Text,
    AsyncStorage,
    Image
} from 'react-native';
import Dimensions from 'Dimensions';
import Spinner from 'react-native-loading-spinner-overlay';
import NavBar from '../common/NavBar'
import TabView from '../Main/TabView'
var Global = require('../common/globals');
import { Button } from 'antd-mobile';

var width = Dimensions.get('window').width;
var index;
export default class LoginView extends Component {
    constructor(props) {
        super(props)
        index = this.props.index;
    }
    state =
    {
        LoginId: '',
        passWord: '',
        loadingVisible: false,
        isTimeout:false,
    }

    componentDidMount() {
        var me = this
        AsyncStorage.getItem('k_last_login_id',function(errs,result)
        {
            if (!errs && result && result.length)
            {
                me.setState({LoginId: result})
            }
            else
            {

            }
        });
    }


    onLoginPress() {
        console.log('LoginId:' + this.state.LoginId + '  password:' + this.state.passWord)
        this.setState({
            loadingVisible: true
        });
        var paramBody = {
                'LoginId': this.state.LoginId,
                'password': this.state.passWord,
                'uuid': 'uc'
            }
        if (!this.state.LoginId.length || !this.state.passWord.length) {
            this.setState({
                loadingVisible: false
            });
            alert('请输入用户名或密码')
        }
        else {
            HttpRequest.get('/hdxt/api/core/authenticate', paramBody, this.onLoginSuccess.bind(this),
                (e) => {
                    this.setState({
                        loadingVisible: false
                    });
                    try {
                        var errorInfo = JSON.parse(e);
                        if (errorInfo != null) {
                            if (errorInfo.code == -1002||
							 errorInfo.code == -1001) {
							alert("账号或密码错误");
						}else {
                            alert(e)
						}

                        } else {
                            alert(e)
                        }
                    }
                    catch(err)
                    {
                        console.log(err)
                    }

                    console.log('Login error:' + e)
                })
        }

        setTimeout(() => {//logout timeout  15s
            if (this.state.loadingVisible == true) {
                this.setState({
                    loadingVisible: false
                });
                alert('登录超时，请稍候再试');
            }
        }, 1000 * 15);

    }

    onLoginSuccess(response) {
        this.setState({
            loadingVisible: false
        });

        console.log('Login success:' + JSON.stringify(response))
        // if (response.access_token.length) {
        //     AsyncStorage.setItem('k_http_token', response.access_token, (error, result) => {
        //         if (error) {
        //             console.log('save http token faild.')
        //         }
        //
        //     });
        // }
        var me = this
        AsyncStorage.setItem('k_login_info', JSON.stringify(response), (error, result) => {
            if (error) {
                console.log('save login info faild.')
            }
            me.setState({hasLogin: true})
            var infoJson = response;
            Global.UserInfo = infoJson.responseResult;
            console.log('UserInfo: ' + result)

        });
        AsyncStorage.setItem('k_last_login_id', this.state.LoginId, (error, result) => {
            if (error) {
                console.log('save login info faild.')
            }
        });


        //show main view
        this.props.navigator.resetTo({
            component: TabView,
            name: 'MainPage'
        })
    }

    render() {
        return (
            <View style={styles.rootcontainer}>
                <NavBar title="登录" />
                <View style={styles.container}>
                    <Image source={require('../images/cni_logo.png')} style={styles.logo} />
                    <TextInput
                        style={styles.LoginId}
                        value={this.state.LoginId}
                         underlineColorAndroid='transparent'
                        editable={true}
                        placeholder={'用户名'}
                        onChangeText={(text) => this.setState({ LoginId: text })}>
                    </TextInput>
                    <TextInput
                        style={styles.passWord}
                         underlineColorAndroid='transparent'
                        value={this.state.passWord}
                        editable={true}
                        secureTextEntry={true}
                        placeholder={'密码'}
                        onChangeText={(text) => this.setState({ passWord: text })}>
                    </TextInput>
                    <TouchableOpacity onPress={this.onLoginPress.bind(this)}
                        style={styles.loginButton}>
                        <Text style={styles.loginText} >
                            登录
                    </Text>
                    </TouchableOpacity>

                    <Spinner
                        visible={this.state.loadingVisible}
                    />
                </View>
            </View>
        )
    }
}

const styles = StyleSheet.create(
    {
        logo: {
            marginTop: 20,
            alignSelf: 'center',
            height: 100,
            width: 100,
            resizeMode: Image.resizeMode.contain,
        },
        container:
        {
            flex: 1,
            flexDirection: 'column',
            justifyContent: 'flex-start',
            alignItems: 'center',
        },
        rootcontainer:
        {
            flex: 1,
            flexDirection: 'column',
            justifyContent: 'flex-start',
            alignItems: 'center',
            backgroundColor: '#ebebeb',
        },

        LoginId:
        {
            alignSelf: 'stretch',
            fontSize: 16,
            textAlign: 'left',
            margin: 10,
            height: 40,
            borderColor: 'gray',
            borderWidth: 1,
            borderRadius: 5,
            paddingLeft: 10,
            alignItems: 'center',
            justifyContent: 'center',
        },

        passWord:
        {
            alignSelf: 'stretch',
            textAlign: 'left',
            color: '#333333',
            marginLeft: 10,
            marginRight: 10,
            fontSize: 16,
            height: 40,
            borderColor: 'gray',
            borderWidth: 1,
            borderRadius: 5,
            paddingLeft: 10,
            alignItems: 'center',
            justifyContent: 'center',
        },
        loginText:
        {
            color: '#ffffff',
            fontSize: 18,
        },
        loginButton:
        {
            marginTop: 30,
            height: 50,
            width: width - 20,
            backgroundColor: '#1a8eaf',
            borderRadius: 5,
            flexDirection: 'row',
            justifyContent: 'center',
            alignItems: 'center',
        },
    });
