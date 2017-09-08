import React, { Component } from 'react';
import {
    StyleSheet,
    Text,
    View,
    Image,
    TouchableOpacity,
    Platform,
    TouchableNativeFeedback,
    TouchableHighlight,
    ListView,
    Picker,
    TextInput
} from 'react-native';

import HttpRequest from '../HttpRequest/HttpRequest'
import NavBar from '../common/NavBar'
import CheckBox from 'react-native-checkbox'
import ModalDropdown from 'react-native-modal-dropdown';
import Dimensions from 'Dimensions';
import MyIssueListView from './MyIssueListView';

var width = Dimensions.get('window').width;


var dataSourceList = []

export default class MyIssueView extends Component {
    constructor(props) {
        super(props)
        var ds = new ListView.DataSource({ rowHasChanged: (r1, r2) => r1 !== r2 });

        dataSourceList = [
        {id:"0",name:"未解决问题",count:0},
        {id:"1",name:"需要处理问题",count:0},{id:"2",name:"已解决问题",count:0},
        {id:"3",name:"发起的问题",count:0},{id:"4",name:"需要确认的问题",count:0},
        {id:"5",name:"关注的问题",count:0},
        ]

        this.state = {
            dataSource: ds.cloneWithRows(dataSourceList)
        }
    }



    componentDidMount() {
        this.getIssuesCount()

    }
    getIssuesCount(){
        var paramBody = {

            }
        HttpRequest.get('/hdxt/api/problem/statistic', paramBody, this.onGetIssueCountSuccess.bind(this),
            (e) => {
                try {
                    alert(e)
                }
                catch (err) {
                    console.log(err)
                }
            })
    }

    onGetIssueCountSuccess(response) {
        console.log('onGetIssueCountSuccess:' + JSON.stringify(response))
        var items = response.responseResult;
        for(var element in items){
            if (element == 'needToSolve') {

                dataSourceList[1].count = items[element].count;
            }else if (element == 'notSolved') {
                dataSourceList[0].count = items[element].count;
            }else if (element == 'created') {
                dataSourceList[3].count = items[element].count;
            }else if (element == 'needConfirm') {
                dataSourceList[4].count = items[element].count;
            }else if (element == 'solved') {
                dataSourceList[2].count = items[element].count;
            }else if (element == 'concernedNotSolved') {
                dataSourceList[5].count = items[element].count;
            }
        }

        var ds = new ListView.DataSource({ rowHasChanged: (r1, r2) => r1 !== r2 });
        this.setState({
            dataSource: ds.cloneWithRows(dataSourceList)
        })
    }

    back() {
        this.props.navigator.pop()
    }


    render() {
        return (
            <View style={styles.container}>
                <NavBar
                    title="我的问题"
                    leftIcon={require('../images/back.png')}
                    leftPress={this.back.bind(this)} />
                {this.renderListView()}

            </View>
        )
    }


     onItemPress(itemData){
         itemData.title = itemData.name;
         this.props.navigator.push({
             component: MyIssueListView,
              props: {
                  data:itemData,
                 }
         })

     }

     displayCount(rowData){
         if (rowData.count > 0) {
             return(<Text style={{ color: '#ff0000' ,paddingLeft:10}}>{rowData.count}</Text>)
         }

     }

    renderRow(rowData, sectionID, rowID) {
        menuView = () => {

                return (
                    <TouchableOpacity onPress={this.onItemPress.bind(this, rowData)}
                    style={{ height: 50, alignItems: 'center', justifyContent: 'center' }}>
                        <View style={{ width: width, height: 50,flexDirection: 'row', alignItems: 'center',justifyContent:'center'}}>
                        <Text style={{ color: '#000000' }}>{rowData.name}</Text>
                        {this.displayCount(rowData)}
                        </View>
                    </TouchableOpacity>
                )
            }

        return (
            <View style={styles.itemView}>
                {menuView()}
            </View>
        )
    }


    renderListView() {
        return (
            <ListView
                style={{ }}
                dataSource={this.state.dataSource}
                renderRow={this.renderRow.bind(this)}
            />
        )
    }


}


const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'flex-start',
        alignItems: 'center',
        backgroundColor: '#F5FCFF',
    },

    divider: {
    backgroundColor: '#8E8E8E',
    width: width,
    height: 0.5,
    }   ,
    itemView:
    {
        alignSelf: 'stretch',
        // justifyContent: 'center',
        justifyContent: 'space-between',
        alignItems: 'center',
        height: 50,
        width: width,
        borderColor: 'gray',
        borderWidth: 0.5,
        flexDirection: 'row',
        backgroundColor: 'white'
    }
})
