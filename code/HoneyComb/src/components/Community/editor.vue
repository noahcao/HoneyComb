<template>
    <div id='editor'>
        <div id='editorNavBar'>
            <button class='barbtn btn-link pull-right'>暂存</button>
            <button class='barbtn btn-link pull-right'>发布</button>
            <button class='barbtn btn-link pull-right' @click='alterEditor'>切换到{{anotherEditor}}</button>
        </div>
        <markdown v-if="!showMarkdown"></markdown>
        <quillEditor v-if="showMarkdown"></quillEditor>
    </div>
</template>

<script>
/* eslint-disable */
import '../../../static/css/quill.core.css'
import '../../../static/css/quill.snow.css'  
import '../../../static/css/quill.bubble.css'
import markdown from './markdown'
import { quillEditor } from 'vue-quill-editor'
export default {
    name: 'outcomb',
    components: {
      markdown,
      quillEditor
    },
    data() {
        return{
            showMarkdown: true,
            anotherEditor: '富文本编辑器'
        }
    },
    methods:{
        alterEditor(){
            if(this.showMarkdown){
                this.showMarkdown = false;
                this.anotherEditor = 'Markdown编辑器';
            }
            else{
                this.showMarkdown = true;
                this.anotherEditor = '富文本编辑器';
            }
        }
    }
}
</script>

<style>
#editorNavBar {
    height: 30px;
    background-color: white;
    width: 90%;
    margin-left: 5%;
    border-bottom-color: black;
    border-bottom: solid 1px;
    padding: 0 4px 0 0
}
#editor {
    height: 100%;
}
.barbtn.btn-link{
    height: 80%;
}
</style>

