<template>
  <div class="mdContainer" >
    <div class="navContainer" v-if="navStatus">
      <div class="markContainer">
        <ul class="markListGroup">
          <li class="markListItem" @click="addStrong" title="strong"><b>B</b></li>
          <li class="markListItem" @click="addItalic" title="italic"><i>I</i></li>
          <li class="markListItem" @click="addStrikethrough" title="strikethrough"><i class="fa fa-strikethrough" aria-hidden="true"></i></li>
          <li class="markListItem" @click="addHTitle(1)" title="H1-title">H1</li>
          <li class="markListItem" @click="addHTitle(2)" title="H2-title">H2</li>
          <li class="markListItem" @click="addHTitle(3)" title="H3-title">H3</li>
          <li class="markListItem" @click="addHTitle(4)" title="H4-title">H4</li>
          <li class="markListItem" @click="addHTitle(5)" title="H5-title">H5</li>
          <li class="markListItem" @click="addHTitle(6)" title="H6-title">H6</li>
          <li class="markListItem" @click="addLine" title="line">一</li>
          <li class="markListItem" @click="addQuote" title="quote"><i class="fa fa-quote-left" aria-hidden="true"></i></li>
          <li class="markListItem" @click="addCode"><i class="fa fa-code" aria-hidden="true"></i></li>
          <li class="markListItem" @click="addLink"><i class="fa fa-link" aria-hidden="true"></i></li>
          <li class="markListItem" @click="addImage"><i class="fa fa-picture-o" aria-hidden="true"></i></li>
          <li class="markListItem" @click="addTable" title="table"><i class="fa fa-table" aria-hidden="true"></i></li>
          <li class="markListItem" @click="addUl" title="ul-list"><i class="fa fa-list-ul" aria-hidden="true"></i></li>
          <li class="markListItem" @click="addOl" title="ol-list"><i class="fa fa-list-ol" aria-hidden="true"></i></li>
          <li class="markListItem" @click="previewFn" title="preview"><i class="fa fa-eye-slash" aria-hidden="true"></i></li>
          <li class="markListItem" @click="previewAllFn" title="previewAll"><i class="fa fa-eye" aria-hidden="true"></i></li>
          <li class="btn" style="margin-left: 80%;margin-right: 5%" @click="Save">暂存</li>
          <li class="btn" @click="Post">发布</li>
        </ul>
      </div>
    </div>
    <div class="mdBodyContainer" :class="{ noMenu: !navStatus }">
      <div class="editContainer" v-if="editStatus">
        <textarea name="" class="mdEditor" @keydown.9="tabFn" v-scroll="editScroll" v-model="input"></textarea>
      </div>
      <div class="previewContainer markdown-body" v-scroll="previewScroll" v-html="htmlValue" v-if="previewStatus">
      </div>
    </div>
  </div>
</template>

<script>
  /* eslint-disable */
  import Vue from 'vue'
  import marked from 'marked'
  import scroll from 'vue-scroll'
  import hljs from '../../../static/js/highlight.min.js'
  import range from '../../../static/js/rangeFn.js'
  Vue.use(scroll)
  marked.setOptions({
    renderer: new marked.Renderer(),
    gfm: true,
    tables: true,
    breaks: false,
    pedantic: false,
    sanitize: true,
    smartLists: true,
    smartypants: false,
    highlight: function(code) {
      return hljs.highlightAuto(code).value
    }
  });

  function insertContent(val, that) {
    let textareaDom = document.querySelector('.mdEditor');
    let value = textareaDom.value;
    let point = range.getCursortPosition(textareaDom);
    let lastChart = value.substring(point - 1, point);
    let lastFourCharts = value.substring(point - 4, point);
    if (lastChart != '\n' && value != '' && lastFourCharts != '    ') {
      val = '\n' + val;
      range.insertAfterText(textareaDom, val);
    } else {
      range.insertAfterText(textareaDom, val);
    }
    that.input = document.querySelector('.mdEditor').value;
  }
  export default {
    name: 'markdown',
    //props: ['mdValuesP', 'fullPageStatusP', 'editStatusP', 'previewStatusP', 'navStatusP', 'icoStatusP'],
    data() {
      return {
        //input: this.msg.mdValue || '',
        input: this.mdValuesP || '',
        editStatus: Boolean(this.editStatusP),
        previewStatus: Boolean(this.previewStatusP),
        fullPageStatus: Boolean(this.fullPageStatusP),
        navStatus: Boolean(this.navStatusP),
        icoStatus: Boolean(this.icoStatusP),
        maxEditScrollHeight:0,
        maxPreviewScrollHeight:0,
        msgShow:'我要显示的内容',
        dilogStatus:false,
        htmlValue: ''
      }
    },
    created: function() {
      if (!this.editStatus && !this.previewStatus) {
        this.editStatus = true;
        this.previewStatus = true;
      }
    },
    mounted(){
      this.input = '# Welcome to HoneyComb';
      //this.mdValuesP=this.msg.mdValue;
      this.fullPageStatusP=false;
      this.editStatusP=true;
      this.previewStatus=true;
      this.previewStatusP=true;
      this.navStatusP=true;
      this.icoStatusP=true;
      this.navStatus=true;
    },
    methods: {
      Save(){
        alert(this.input);
        alert(this.htmlValue)
      },
      Post(){
        alert(this.input);
        alert(this.htmlValue)
      },
      tabFn: function(evt) {
        insertContent("    ", this);
        // 屏蔽屌tab切换事件
        if (evt.preventDefault) {
          evt.preventDefault();
        } else {
          evt.returnValue = false;
        }
      },
      addImage: function(evt) {
        insertContent("![Vue](https://cn.vuejs.org/images/logo.png)", this);
      },
      addHTitle: function(index) {
        let tmp = '';
        switch (index) {
          case 1:
            tmp = '# ';
            break;
          case 2:
            tmp = '## ';
            break;
          case 3:
            tmp = '### ';
            break;
          case 4:
            tmp = '#### ';
            break;
          case 5:
            tmp = '##### ';
            break;
          case 6:
            tmp = '###### ';
            break;
          default:
            break;
        }
        insertContent(tmp, this);
      },
      addCode: function() {
        let textareaDom = document.querySelector('.mdEditor');
        let value = textareaDom.value;
        let point = range.getCursortPosition(textareaDom);
        let lastChart = value.substring(point - 1, point);
        insertContent('```\n\n```', this);
        if (lastChart != '\n' && value != '') {
          range.setCaretPosition(textareaDom, point + 5);
        } else {
          range.setCaretPosition(textareaDom, point + 4);
        }
      },
      addStrikethrough: function() {
        let textareaDom = document.querySelector('.mdEditor');
        let value = textareaDom.value;
        let point = range.getCursortPosition(textareaDom);
        let lastChart = value.substring(point - 1, point);
        insertContent('~~~~', this);
        if (lastChart != '\n' && value != '') {
          range.setCaretPosition(textareaDom, point + 3);
        } else {
          range.setCaretPosition(textareaDom, point + 2);
        }
      },
      addStrong: function() {
        let textareaDom = document.querySelector('.mdEditor');
        let value = textareaDom.value;
        let point = range.getCursortPosition(textareaDom);
        let lastChart = value.substring(point - 1, point);
        insertContent('****', this);
        if (lastChart != '\n' && value != '') {
          range.setCaretPosition(textareaDom, point + 3);
        } else {
          range.setCaretPosition(textareaDom, point + 2);
        }
      },
      addItalic: function() {
        let textareaDom = document.querySelector('.mdEditor');
        let value = textareaDom.value;
        let point = range.getCursortPosition(textareaDom);
        let lastChart = value.substring(point - 1, point);
        insertContent('**', this);
        if (lastChart != '\n' && value != '') {
          range.setCaretPosition(textareaDom, point + 2);
        } else {
          range.setCaretPosition(textareaDom, point + 1);
        }
      },
      setStrong: function() {
        let textareaDom = document.querySelector('.mdEditor');
        let point = range.getCursortPosition(textareaDom);
      },
      addLine: function() {
        insertContent('\n----\n', this);
      },
      addLink: function() {
        insertContent("[Vue](https://cn.vuejs.org/images/logo.png)", this);
      },
      addQuote: function() {
        let textareaDom = document.querySelector('.mdEditor');
        let value = textareaDom.value;
        let point = range.getCursortPosition(textareaDom);
        let lastChart = value.substring(point - 1, point);
        insertContent('> ', this);
        if (lastChart != '\n' && value != '') {
          range.setCaretPosition(textareaDom, point + 3);
        } else {
          range.setCaretPosition(textareaDom, point + 2);
        }
      },
      addTable: function() {
        insertContent('\nheader 1 | header 2\n', this);
        insertContent('---|---\n', this);
        insertContent('row 1 col 1 | row 1 col 2\n', this);
        insertContent('row 2 col 1 | row 2 col 2\n\n', this);
      },
      addUl: function() {
        insertContent('* ', this);
      },
      addOl: function() {
        insertContent('1. ', this);
      },
      previewFn: function() {
        if (!this.editStatus) {
          this.editStatus = true;
          this.previewStatus = !this.previewStatus;
        } else {
          this.previewStatus = !this.previewStatus;
        }
      },
      previewAllFn: function() {
        if (!this.editStatus && this.previewStatus) {
          this.editStatus = true;
          this.previewStatus = true;
        } else {
          this.editStatus = false;
          this.previewStatus = true;
        }
      },
      previewScroll: function(e, position) {
        if(this.maxEditScrollHeight!==0){
          let topPercent=position.scrollTop/this.maxPreviewScrollHeight;
          document.querySelector('.mdEditor').scrollTop = this.maxEditScrollHeight*topPercent;
        }
      },
      editScroll:function(e, position){
        if(this.maxPreviewScrollHeight!==0){
          let topPercent=position.scrollTop/this.maxEditScrollHeight;
          document.querySelector('.previewContainer').scrollTop = this.maxPreviewScrollHeight*topPercent;
        }
      },
      happyDay:function(){
        window.open('https://github.com/ovenslove/vue-mdEditor');
      }
    },

    watch: {
      input: function() {
        this.mdValue = this.input;
        this.htmlValue = marked(this.input, {
          sanitize: true
        });
        let maxEditScrollHeight=document.querySelector('.mdEditor').scrollHeight-document.querySelector('.mdEditor').clientHeight;
        let maxPreviewScrollHeight=document.querySelector('.previewContainer').scrollHeight-document.querySelector('.previewContainer').clientHeight;
        this.maxEditScrollHeight = maxEditScrollHeight;
        this.maxPreviewScrollHeight = maxPreviewScrollHeight;
      }
    }
  }
</script>

<style scoped>
  @import '../../../static/css/editor.css';
</style>

