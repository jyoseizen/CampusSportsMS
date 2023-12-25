<template>
  <div
    style="padding: 10px 10px 10px 10px; margin: 3px 0px 0px 3px;box-shadow: 0 0 5px #888888;background-color: #f0f2fa;">
    <!--    <div class="draft">-->
    <!--      <div style="width: 14%;height: 200px;border: 2px solid #9c88ff;margin-left:10px;">-->
    <!--        <div class="icon">头像</div>-->
    <!--        <div class="name">名字</div>-->
    <!--      </div>-->
    <!--    </div>-->
    <!--    <div class="col">-->
    <!--      <div class="college">学院</div>-->
    <!--    </div>-->

    <div class="block" v-for="(item,index) in draftList" :key="index">
      <div style="width: 14%;height: 185px;margin: 8px 8px 8px 18px;">
        <div style="width: 118px;height: 118px; border-radius:50%;margin-left:25px">
          <img :src="img+item.usersList[0].usersUrl" alt="" class="img">
        </div>
        <div class="nameclass">{{ item.usersName }}</div>
      </div>
      <div
        style="width: 80%;height: 185px;margin-left: 10px;margin: 8px;background-color: #ffffff;border-radius :3px;box-shadow: 0 0 3px #D3D3D3;">
        <div style="display: flex;">  <!-- 分栏-->
          <div style="width: 80px;height: 30px;">

            <el-tag size="medium" style="margin-top: 6px;margin-left :9px">{{ item.collegeName }}</el-tag>
          </div>
          <div style="width: 32px;height: 32px;margin-left:750px;margin-top:4px"><img src="static/img/1.png"></div>
<!--          <div style="width: 32px;height: 32px;margin-left:750px;margin-top:4px"><img :src='imgUrl' v-if="res.data"/></div>-->
          <div style="width: 32px;height: 32px;margin-left:15px;margin-top:4px"><img src="static/img/像素_树.png"></div>
          <div style="width: 32px;height: 32px;margin-left:15px;margin-top:4px"><img src="static/img/像素_游戏机.png"></div>
        </div>
        <div class="texts">
          <textarea rows="5.7" cols="105" class="textarea-text" readonly>{{ item.draftContent }}</textarea></div>
      </div>
      <!--      <HR style="align:center;width:300px; color:#987cb9;">-->

    </div>
    <!--    <hr width="300" size="20" noshade="noshade" align="right" color="#0000FF" />-->

    <!--    分页-->
    <div style="margin: 10px 0">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[3, 6, 20]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import {UDgetLisetAPI} from '@/request/api';

export default {
  data() {
    return {
      //分页
      currentPage: 1,
      pageSize: 3,
      total: 0,

      draftList: [],
      img: "http://localhost:9090/image/",

      // imgUrl:'static/img/1.png',
      imgUrl:'',

    }
  },

  created() {
    this.getList()
  },



  methods: {
    getRandomInt(min, max) {
      // 以下函数返回 min（包含）～ max（包含）之间的数字：
      this.data = Math.floor(Math.random() * (max - min + 1)) + min

      //  函数返回 min（包含）～ max（不包含）之间的数字
      //  this.data = Math.floor(Math.random() * (max - min) ) + min;
    },

    //分页请求
    getList() {
      UDgetLisetAPI({
        params: {
          PageNum: this.currentPage,
          PageSize: this.pageSize,
          search: this.search
        }
      }).then(res => {

        console.log("=====>>", res.data)
        // console.log(res.data.data.records)

        this.total = res.data.data.total
        this.draftList = res.data.data.records
        // this.draftList.imge = res.data.data.records[0].usersList[0].usersUrl
        // var i=0
        for(var i=0;i<this.draftList.length;i++){
          var n = Math.round(Math.random()*(3-1)+1)
          console.log("--->", this.imgUrl)
          console.log("--->", this.index)
          this.imgUrl='static/img/'+[n]+'.png'
        }

        // this.img = "http://localhost:9090/image/"+res.data.data.records[0].usersList[0].usersUrl

        //比赛条数

        // console.log("=====>》》》》>",this.draftList.img)


        console.log("=====>》》》》>", this.draftList)

      })

    },

    //最大条数
    sizeChange(pageSize) {
      this.page.pageSize = pageSize
    },
    //改变当前每页的个数触发
    handleSizeChange(pageSize) {
      this.pageSize = pageSize;
      this.getList()
    },
    //改变当前页码触发
    handleCurrentChange(pageNum) {
      this.currentPage = pageNum
      this.getList()
    },
  }
}

</script>

<style scoped>
.block {
  width: 99.5%;
  height: 200px;
  /*border-bottom: solid 1px green;*/
  /*border-top: solid 1px green;*/
  margin-bottom:4px;
  display: flex;
  border-radius: 10px;

  box-shadow: 0 0 2px #CCCCCC;
  /*box-shadow: 0 0 2px #CCCCCC,*/
  /*0 0 0 #f0f2fa,*/
  /*0 0 2px #CCCCCC,*/
  /*0 0 0 #f0f2fa;*/
}

.img {
  width: 120px;
  height: 120px;
  border-radius: 50%;
  box-shadow: 0 0 2px #CCCCCC;
}

.nameclass {
  width: 171px;
  height: 45px;
  /*border: 2px solid #e2ae2b;*/
  margin-top: 10px;
  /*font-family: "Arial","Microsoft YaHei","黑体","宋体",sans-serif;*/
  font-family: "lucida grande", "lucida sans unicode", lucida, helvetica, "Hiragino Sans GB", "Microsoft YaHei", "WenQuanYi Micro Hei", sans-serif;
  font-size: 20px;
  padding-top: 5px;

  /*!*font-family: Helvetica, 'Hiragino Sans GB', 'Microsoft Yahei', '微软雅黑', Arial, sans-serif;*!font: 14px/1.5 "Helvetica Neue",Helvetica,Arial,"Microsoft Yahei","Hiragino Sans GB","Heiti SC","WenQuanYi Micro Hei",sans-serif;*/
}

.texts {
  width: 99.5%;
  height: 140px;
  /*padding:5px;*/
  /*padding-top:6px;*/
  /*border: 2px solid #ffbf00;*/
  /*margin-top: 5px;*/
  background-color: #ffffff;

}

.textarea-text {
  /*width: 98%;*/
  height: 90%;
  margin-top: 5px;
  font-size: 14px;
  color: #555f77;
  font-family: "lucida grande", "lucida sans unicode", lucida, helvetica, "Hiragino Sans GB", "Microsoft YaHei", "WenQuanYi Micro Hei", sans-serif;
  font-size: 15px;
  /*去掉文本域边框线*/
  border: none;
  resize: none;
  cursor: pointer;
}

.draft {

  width: 1260px;
  height: 200px;
  display: flex;
  /*float: left;*/
  border-radius: 10px; /* div圆角*/
  box-shadow: 0 0 20px #E8E8E8;
  border: 2px solid #009999;
}

.icon {
  width: 120px;
  height: 120px;
  margin: 10px 0px 10px 25px;
  border: 2px solid #ADFF2F;
}

.name {
  width: 174px;
  height: 48px;
  border: 2px solid #ADFF2F;
}

.college {
  width: 174px;
  height: 48px;
  border: 2px solid #ADFF2F;
  /*margin-top:100px;*/
}

.col {
  width: 60%;
  height: 199px;
  border: 2px solid #409EFF;
  margin-left: 230px;
  /*display:flex;*/
}
</style>






