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
    ScrollView
} from 'react-native';

import Dimensions from 'Dimensions';
import NavBar from '../common/NavBar'
import px2dp from '../common/util'
import dateformat from 'dateformat'
import HttpRequest from '../HttpRequest/HttpRequest'
import MainFirstDetailView from './MainFirstDetailView';
import Banner from 'react-native-banner';

const REQUST_TASK_STATUS_URL = "/hdxt/api/statistics/task/status"
import { Badge,Grid } from 'antd-mobile';

const isIOS = Platform.OS == "ios"
var width = Dimensions.get('window').width;

var yesterday = new Date()
yesterday.setTime(yesterday.getTime() - 24 * 60 * 60 * 1000)
var tomorrow = new Date()
tomorrow.setTime(tomorrow.getTime() + 24 * 60 * 60 * 1000)


var toolsData = [
    {
        'index': 0,
        'title': '管道计划',
        "type": "4",
        'image': require('../images/icon_plan.png')
    },
    {
        'index': 1,
        'title': '通风计划',
        "type": "2",
        'image': require('../images/icon_finish.png')
    },
    {
        'index': 2,
        'title': '机械计划',
        "type": "3",
        'image': require('../images/icon_unfinish.png')
    },
    {
        'index': 3,
        'title': '电气计划',
        "type": "1",
        'image': require('../images/icon_task.png')
    },
    {
        'index': 4,
        'title': '仪表计划',
        "type": "4",
        'image': require('../images/icon_deal.png')
    },
    {
        'index': 5,
        'title': '主系统',
        "type": "5",
        'image': require('../images/icon_deal.png')
    },
    {
        'index': 6,
        'title': '保温计划',
        "type": "6",
        'image': require('../images/icon_deal.png')
    }
]

var taskStatus = {}

var daySegArr = ['' + yesterday.getDate() + '日', '今日', '' + tomorrow.getDate() + '日', '周', '月', '年']
let typeSegArr = ['焊口', '支架']
let dayCateArr = ['dateBefore', 'dateCurrent', 'dateAfter', 'dateWeek', 'dateMonth', 'dateYear']

export default class HomeView extends Component {
    constructor(props) {
        super(props)
        this.state = {
            selectedDayIndex: 1,
            selectedTypeIndex: 0,
            banners:[],
        };
    }

    componentWillMount(){
        this.fetchBanner();

    }

    fetchBanner(){
    var paramBody ={ }
    HttpRequest.get('/banner', paramBody, this.onBannerSuccess.bind(this),
        (e) => {

            try {
                var errorInfo = JSON.parse(e);
                console.log(errorInfo.description)
                if (errorInfo != null && errorInfo.description) {
                    console.log(errorInfo.description)
                } else {
                    console.log(e)
                }
            }
            catch(err)
            {
                console.log(err)
            }

            console.log(' error:' + e)
        })
}

    bannerClickListener(index) {
    this.setState({
            clickTitle: this.state.banners[index].title ? `you click ${this.state.banners[index].title}` : 'this banner has no title',
        })

    }

    bannerOnMomentumScrollEnd(event, state) {
       //  console.log(`--->onMomentumScrollEnd page index:${state.index}, total:${state.total}`);
        this.defaultIndex = state.index;
    }

    componentDidMount() {
        setTimeout(() => {
            this.getTaskStatus(this.state.selectedDayIndex)
        }, 1000 * 0.2);

    }


    onBannerSuccess(response){
        this.state.banners = response.data.images;
        console.log('BannerSuccess:' + JSON.stringify(response.data));
        this.setState({banners:this.state.banners});
    }

    getTaskStatus(index) {
        var date = new Date()
        var now = dateformat(date, 'yyyy-mm-dd')
        var category = dayCateArr[index]

        var paramBody = {
                'taskDate': now,
                'category': category
            }
        HttpRequest.get(REQUST_TASK_STATUS_URL, paramBody, this.onGetTaskSuccess.bind(this),
            (e) => {
                try {
                    alert(e)
                }
                catch (err) {
                    console.log(err)
                }
            })
    }

    onGetTaskSuccess(response) {
        console.log('onGetTaskSuccess:' + JSON.stringify(response))
        response.responseResult.retuls.map((item, i) => {
            if (item.type) {
                taskStatus[item.type] = item
            }
        })

        this.setState({
            ...this.state
        })
    }

    onToolsItemClick(index) {
        console.log('Did click item at:' + index)
            //

            this.props.navigator.push({
                component: MainFirstDetailView,
                 props: {
                     data:toolsData[index],
                     type:this.state.selectedTypeIndex,
                     typeStr:typeSegArr[this.state.selectedTypeIndex],
                     category:dayCateArr[index],
                    }
            })

    }

    onDayIndexChange(index) {

        this.setState({
            selectedDayIndex: index
        })

        this.getTaskStatus(index)
    }

    onTypeIndexChange(index) {

        this.setState({
            selectedTypeIndex: index
        })
    }

    render() {
        return (
            <View style={styles.container}>
                <NavBar title="动态监管系统" />
                 <ScrollView
                 keyboardDismissMode='on-drag'
                 keyboardShouldPersistTaps='always'
                 showsHorizontalScrollIndicator = {false}
                 showsVerticalScrollIndicator={false}
                 horizontal={false}
                 style={{width:width}}
                 >

                {this.renderTopView()}
                {this.renderToolsView()}
                 </ScrollView>
            </View>
        )
    }


    renderTopView() {
        var banners = JSON.stringify(this.state.banners);
        console.log("this.state.banners="+banners)
        if (this.state.banners.length == 0) {
            return (
                <Image source={require('../images/logo.png')} style={styles.topView} resizeMode={Image.resizeMode.contain} />

            )
        }else{
            return (

                <Banner
                    style={styles.topView}
                    banners={this.state.banners}
                    defaultIndex={this.defaultIndex}
                    onMomentumScrollEnd={this.bannerOnMomentumScrollEnd.bind(this)}
                    intent={this.bannerClickListener.bind(this)}
                />


            )
        }
    }

    renderToolsView() {
        return(
            <Grid data={toolsData}
            columnNum={4}
             hasLine={false}
             renderItem={item => (

                 //format title
                 <TouchableHighlight style={{ alignSelf:'stretch',flex:1 }} key={item.index} onPress={() => { this.onToolsItemClick(item.index) }}>
                 <View style={[{ alignSelf:'stretch',flex:1 }, styles.toolsItem]}>

                     <Badge dot>
                     <Image source={item.image} style={{ marginBottom: 10, width: 48, height: 48 }} resizeMode={Image.resizeMode.contain} />
                     </Badge>

                     <Text style={{ fontSize: px2dp(12), color: "#666" }}>{item.title}</Text>
                 </View>
                 </TouchableHighlight>


             )}

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
    topView: {
        height: 150,
        width: width,
        alignSelf:'stretch'
    },
    segmentView: {
        height: 44,
        borderColor: '#00a629'
    },
    segmentSelectedView: {
        backgroundColor: '#00a629'
    },
    toolsView: {

        backgroundColor: "#fff",
        flexDirection: "row",
        flexWrap: "wrap"
    },
    toolsItem: {
        backgroundColor: "#fff",
        justifyContent: "center",
        alignItems: "center"
    },
});
