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
import MyWitnessReceiveListView from './MyWitnessReceiveListView'
import ModalDropdown from 'react-native-modal-dropdown';
import Dimensions from 'Dimensions';

var width = Dimensions.get('window').width;

var dataSourceList = []
var dataSourceList2 = []
var datas = []
export default class MyWitnessByTypeView extends Component {
    constructor(props) {
        super(props)
        var ds = new ListView.DataSource({ rowHasChanged: (r1, r2) => r1 !== r2 });

        dataSourceList = [
        {id:"QC1",name:"我收到的见证QC1"},
        {id:"QC2",name:"我收到的见证QC2"},{id:"A",name:"我收到的见证"},
        {id:"B",name:"我收到的见证B"},{id:"C",name:"我收到的见证C"},
        {id:"D",name:"我收到的见证B"},
        ]
        dataSourceList2 = [
        {id:"QC1",name:"我完成的见证QC1"},
        {id:"QC2",name:"我完成的见证QC2"},{id:"A",name:"我完成的见证"},
        {id:"B",name:"我完成的见证B"},{id:"C",name:"我完成的见证C"},
        {id:"D",name:"我完成的见证B"},
        ]
        datas = dataSourceList;

        if (!this.props.isReceiveWitness) {
            datas = dataSourceList2;
        }




        this.state = {
            dataSource: ds.cloneWithRows(datas)
        }
    }




        componentDidMount() {
            this.getWitnessCount()

        }
        getWitnessCount(){
            var paramBody = {

                }
            HttpRequest.get('/hdxt/api/baseservice/witness/statistic', paramBody, this.onGetWitnessCountSuccess.bind(this),
                (e) => {
                    try {
                        alert(e)
                    }
                    catch (err) {
                        console.log(err)
                    }
                })
        }

        onGetWitnessCountSuccess(response) {
            console.log('onGetWitnessCategoryCountSuccess:' + JSON.stringify(response))
            var items = response.responseResult;
            for(var element in items){
                if (element == 'myeventComplete') {
                    //qc1,qc2, a, b, c, b
                    datas[0].count= items[element].count_qc1;
                    datas[1].count= items[element].count_qc2;
                    datas[2].count= items[element].count_a;
                    datas[3].count= items[element].count_b;
                    datas[4].count= items[element].count_c;
                    datas[5].count= items[element].count_d;
                }else if (element == 'assign') {
                    datas[0].count= items[element].count_assign_qc1;
                    datas[1].count= items[element].count_assign_qc2;
                    datas[2].count= items[element].count_assign_a;
                    datas[3].count= items[element].count_assign_b;
                    datas[4].count= items[element].count_assign_c;
                    datas[5].count= items[element].count_assign_d;
                }
            }

            var ds = new ListView.DataSource({ rowHasChanged: (r1, r2) => r1 !== r2 });
            this.setState({
                dataSource: ds.cloneWithRows(datas)
            })
        }


    back() {
        this.props.navigator.pop()
    }


    render() {
        return (
            <View style={styles.container}>
                <NavBar
                    title={this.props.title}
                    leftIcon={require('../images/back.png')}
                    leftPress={this.back.bind(this)} />
                {this.renderListView()}

            </View>
        )
    }


 onItemPress(itemData){
     if (this.props.isReceiveWitness) {
         this.props.navigator.push({
             component: MyWitnessReceiveListView,
              props: {
                  data:itemData,

                 }
         })
     }

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
