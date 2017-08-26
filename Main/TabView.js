import React, { Component } from 'react';
import {
    AppRegistry,
    StyleSheet,
    Text,
    View,
    Image,
    Navigator,
    BackAndroid
} from 'react-native';


import HomeView from './HomeView';
import MeView from './MeView';
import TaskConfirmView from './TaskConfirmView';
import PlanView from './PlanView';
import Navigation from '../common/Navigation';
import TabNavigator from 'react-native-tab-navigator';

export default class TabView extends Component
{
    state =
    {
        selectedTab: 'tab1'
    }

    componentWillMount(){
        var me = this;
        BackAndroid.addEventListener('harwardBackPress', () => {
            const routers = me.props.navigator.getCurrentRoutes();
            if (routers.length > 1) {
                me.props.navigator.pop();
                return true;
            } else {
                    if (routers[0].name == 'MainPage'||routers[0].name == 'LoginView') {
                      BackAndroid.exitApp();
                      return true;
                    } else {
                      me.props.navigator.pop();
                      return true;
                    }

                  }
                  return false;
      });
    }


      componentWillUnmount() {
                BackAndroid.removeEventListener('hardwareBackPress');
            }

    render()
    {
        return (
            <TabNavigator>
                <TabNavigator.Item
                    selected={this.state.selectedTab === 'tab1'}
                    title="首页"
                    renderIcon={() => <Image style={{width:24,height:24,}} source={require('../images/home.png')} />}
                    renderSelectedIcon={() => <Image style={{width:24,height:24,}} source={require('../images/homeg.png')} />}
                    badgeText=""
                    selectedTitleStyle={styles.tabBarTintColor}
                    onPress={() => this.setState({ selectedTab: 'tab1' })}>
                    {<HomeView {...this.props}/>}
                </TabNavigator.Item>
                <TabNavigator.Item
                    selected={this.state.selectedTab === 'tab2'}
                    title="计划分配"
                    renderIcon={() => <Image style={{width:24,height:24,}} source={require('../images/jhfph.png')} />}
                    renderSelectedIcon={() => <Image style={{width:24,height:24,}} source={require('../images/jhfpl.png')} />}
                    selectedTitleStyle={styles.tabBarTintColor}
                    onPress={() => this.setState({ selectedTab: 'tab2' })}>
                    {<PlanView {...this.props}/>}
                </TabNavigator.Item>
                <TabNavigator.Item
                    selected={this.state.selectedTab === 'tab3'}
                    title="施工确认"
                    renderIcon={() => <Image
                        style={{width:24,height:24,}}
                         source={require('../images/sgqrh.png')} />}
                    renderSelectedIcon={() => <Image style={{width:24,height:24,}} source={require('../images/sgqrl.png')} />}
                    selectedTitleStyle={styles.tabBarTintColor}
                    onPress={() => this.setState({ selectedTab: 'tab3' })}>
                    {<TaskConfirmView {...this.props}/>}
                </TabNavigator.Item>
                <TabNavigator.Item
                    selected={this.state.selectedTab === 'tab4'}
                    title="我的"
                    renderIcon={() => <Image style={{width:24,height:24,}} source={require('../images/me.png')} />}
                    renderSelectedIcon={() => <Image style={{width:24,height:24,}} source={require('../images/meh.png')} />}
                    selectedTitleStyle={styles.tabBarTintColor}
                    onPress={() => this.setState({ selectedTab: 'tab4' })}>
                    {<MeView {...this.props}/>}
                </TabNavigator.Item>

            </TabNavigator>

        )
    }
}


const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: '#F5FCFF',
    },
    welcome: {
        fontSize: 20,
        textAlign: 'center',
        margin: 10,
    },
    instructions: {
        textAlign: 'center',
        color: '#333333',
        marginBottom: 5,
    },
    tabBarTintColor: {

      color: '#00a629'
    },

});
