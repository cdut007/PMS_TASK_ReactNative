import React, { Component } from 'react';
import {
    StyleSheet,
    Text,
    View,
    Image,
    TouchableOpacity,
    Platform,
    ListView,
    ActivityIndicator,
    TouchableNativeFeedback,
    TouchableHighlight,
} from 'react-native';


import {
    MKIconToggle,
    MKSwitch,
    MKRadioButton,
    MKCheckbox,
    MKColor,
    getTheme,
    setTheme,
} from 'react-native-material-kit'
import PlanDeliverHeaderView from '../common/PlanDeliverHeaderView'
import NavBar from '../common/NavBar'
import DateTimePickerView from '../common/DateTimePickerView'
import Dimensions from 'Dimensions'
import HttpRequest from '../HttpRequest/HttpRequest'

var Global = require('../common/globals')
var width = Dimensions.get('window').width
var teamArr = []
var taskArr = []

export default class MyPlanView extends Component {

    constructor(props) {
        super(props)

        this.isTeam = this.matchRoles('/construction/team')
        this.isEndman = this.matchRoles('/construction/endman')
        this.isPersonal = !this.isEndman && !this.isTeam
        this.pagenum = 1
        this.ds = new ListView.DataSource({ rowHasChanged: (r1, r2) => r1 !== r2 });
        setTheme({
            checkboxStyle: {
                fillColor: MKColor.Teal,
                borderOnColor: MKColor.Teal,
                borderOffColor: MKColor.Teal,
                rippleColor: `rgba(${MKColor.RGBTeal},.15)`,
            }
        })

        this.state = {
            teamNameArr: [],
            selectedTeam: { 'id': -1, 'name': '选择班组' },
            dataSource: this.ds.cloneWithRows(taskArr),
        }
    }

    componentDidMount() {
        if (this.isPersonal) {
            this.getTask()
        }
        else {
            this.getTeam()

            if (this.isEndman) {
                this.getTask()
            }
        }
    }

    back() {
        this.props.navigator.pop()
    }

    onSelectedDate(date) {

    }

    index(rowID) {
        var index = parseInt(rowID) + 1

        return index;
    }

    onItemPress(rowData) {

    }
    onGetTeamSuccess(response) {

        var tempArr = []
        if (response['code'] == '1000') {
            response['responseResult'].map((item, i) => {
                tempArr.push(item.department)
            })
        }

        teamArr = tempArr
        this.updateTeamPicker()
    }

    onGetTaskSuccess(response) {
        if (response['code'] == '1000') {
            if (response.responseResult.currentPage == 1) {
                taskArr.splice(0, taskArr.length)
            }

            taskArr = taskArr.concat(response.responseResult['datas'])
            console.log('~~~taskArr:' + JSON.stringify(taskArr) + '      ~~~' + JSON.stringify(response.responseResult['datas']))
            this.setState({
                dataSource: this.ds.cloneWithRows(taskArr)
            })
        }
    }

    onCommit()
    {

    }

    updateTeamPicker() {
        var nameArr = []
        teamArr.map((item, i) => {
            nameArr.push(item.name)
        })

        this.setState({
            teamNameArr: nameArr
        })
    }

    matchRoles(uri) {
        var roles = Global.UserInfo.privileges;
        if (roles) {
            for (var i in roles) {
                if (roles[i].uri == uri) {
                    console.log('match the roles' + uri);
                    return true;
                }
            }
        }

        return false;
    }

    getTask() {
        var requestUrl
        var param = {
            'condition': 'equal',
            'pagesize': 10,
            'pagenum': this.pagenum
        }

        if (this.isTeam) {
            param['teamId'] = this.state.selectedTeam.id
            requestUrl = '/hdxt/api/baseservice/construction/team'
        }
        else if (this.isEndman) {
            requestUrl = 'hdxt/api/baseservice/construction/endman/'
        }
        else {
            requestUrl = '/hdxt/api/baseservice/construction/mytask'
        }

        HttpRequest.get(requestUrl, param, this.onGetTaskSuccess.bind(this),
            (e) => {
                try {
                    var errorInfo = JSON.parse(e);
                    if (errorInfo != null) {
                        console.log(errorInfo)
                    } else {
                        console.log(e)
                    }
                }
                catch (err) {
                    console.log(err)
                }

                console.log('Plan error:' + e)
            })
    }

    getTeam() {

        this.setState({
            isLoading: true,
            isLoadingTail: false,
        })

        var paramBody = {}
        var requestUrl = '/hdxt/api/statistics/task';
        if (this.isEndman) {
            requestUrl = '/hdxt/api/statistics/teamgroup/task';
        }

        HttpRequest.get(requestUrl, paramBody, this.onGetTeamSuccess.bind(this),
            (e) => {
                try {
                    var errorInfo = JSON.parse(e);
                    if (errorInfo != null) {
                        console.log(errorInfo)
                    } else {
                        console.log(e)
                    }
                }
                catch (err) {
                    console.log(err)
                }

                console.log('Plan error:' + e)
            })
    }

    onSelectDropDown(idx, value) {
        this.setState({ selectedTeam: teamArr[idx] })
    }

    render() {
        return (
            <View style={styles.container}>
                <NavBar title={'我的计划'}
                    leftIcon={require('../images/back.png')}
                    leftPress={this.back.bind(this)}
                />
                <PlanDeliverHeaderView />
                {this.renderDatePicker()}
                {this.renderTeamPicker()}
                {this.renderListView()}
                <TouchableOpacity onPress={this.onCommit.bind(this)}
                    style={styles.commitButton}>
                    <Text style={{ color: '#ffffff', fontSize: 20, }} >
                        确认提交
                    </Text>
                </TouchableOpacity>
            </View>
        )
    }

    renderDatePicker() {
        if (this.isPersonal) {
            return (<View />)
        }
        else {
            return (
                <DateTimePickerView
                    type={'date'}
                    onSelected={(date) => { this.onSelectedDate(date) }}
                />
            )
        }
    }

    renderTeamPicker() {
        if (this.isPersonal) {
            return (<View />)
        }
        else {
            return (
                <ModalDropdown
                    options={this.state.teamNameArr}
                    textStyle={{ alignSelf: 'stretch', paddingLeft: 5 }}
                    dropdownStyle={styles.dropDownList}
                    style={styles.dropDown}
                    defaultValue={this.state.selectedTeam['name']}
                    onSelect={(idx, value) => this.onSelectDropDown(idx, value)} >
                </ModalDropdown>
            )
        }
    }

    renderListView() {
        return (
            <ListView
                style={{ flex: 1 }}
                dataSource={this.state.dataSource}
                renderRow={this.renderRow.bind(this)}
                automaticallyAdjustContentInsets={false}
                keyboardDismissMode="on-drag"
                keyboardShouldPersistTaps={'always'}
                showsVerticalScrollIndicator={false}
            />
        )
    }

    renderRow(rowData, sectionID, rowID) {
        menuView = () => {
            return (
                <View style={styles.itemContainer}>
                    <TouchableOpacity style={{ flexDirection: 'row', flex: 7 }} onPress={this.onItemPress.bind(this, rowData)}>
                        <View style={styles.cell}>
                            <Text style={styles.content}>
                                {this.index(rowID)}
                            </Text>
                        </View>
                        <View style={styles.cell2}>
                            <Text style={styles.content}>
                                {rowData.weldno}
                            </Text>
                        </View>
                        <View style={styles.cell3}>
                            <Text style={styles.content}>
                                {rowData.drawno}
                            </Text>
                        </View>
                    </TouchableOpacity>
                    <View style={styles.cell4}>
                        <MKCheckbox checked={true} />
                    </View>
                </View>

            )

        }
        return (
            <View style={styles.itemView}>
                {menuView()}
            </View>
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
    dropDown: {
        justifyContent: 'center',
        // flex: 1,
        width: 120,
        height: 36,
        backgroundColor: 'lightgrey',
        marginLeft: 20,
        borderColor: 'lightgray',
        borderWidth: 0.5,
    },
    dropDownList: {
        width: 119,
        borderColor: 'lightgray',
        borderWidth: 0.5,
    },
    content: {
        color: '#000000',
        fontSize: 16,
    },
    itemContainer: {
        height: 50,
        width: width,
        flexDirection: 'row',
        backgroundColor: 'white',
        borderBottomWidth: 0.5,
        borderColor: '#8E8E8E'
    },
    cell: {
        flex: 1,
        height: 50,
        justifyContent: "center",
        alignItems: 'center',
        borderRightWidth: 0.5,
        borderColor: '#8E8E8E'
    },
    cell2: {
        flex: 2,
        height: 50,
        justifyContent: "center",
        alignItems: 'center',
        borderRightWidth: 0.5,
        borderColor: '#8E8E8E'
    },
    cell3: {
        flex: 4,
        height: 50,
        justifyContent: "center",
        alignItems: 'center',
        borderRightWidth: 0.5,
        borderColor: '#8E8E8E'
    },
    cell4: {
        flex: 1,
        height: 50,
        justifyContent: "center",
        alignItems: 'center',
    },
    commitButton:
    {
        height: 50,
        width: width,
        backgroundColor: '#00a629',
        flexDirection: 'row',
        justifyContent: 'center',
        alignItems: 'center',
    },
})