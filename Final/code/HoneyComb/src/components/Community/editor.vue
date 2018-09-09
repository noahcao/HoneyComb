<template>
    <div id='editor'>
        <div id='editorNavBar'>
            <button class='barbtn btn-link pull-right' @click="saveContent">Save</button>
            <button class='barbtn btn-link pull-right' @click="postContent">Post</button>
            <button class='barbtn btn-link pull-right' @click='alterEditor'>{{anotherEditor}}</button>
        </div>
        <markdown v-if="showMarkdown"></markdown>
        <quill-editor class="quill" v-model="data.EditorContent"
        v-if="!showMarkdown"></quill-editor>
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
    name: 'editor',
    props: ['panelId'],
    components: {
      markdown,
      quillEditor,
    },
    data() {
        return{
            showMarkdown: false,
            anotherEditor: 'Markdown Mode',
        }
    },
    methods:{
        alterEditor(){
            if(this.showMarkdown){
                this.showMarkdown = false;
                this.anotherEditor = 'MarkDown Mode';
                this.data.EditorContent = "";
                this.data.inMarkdown = true
            }
            else{
                this.showMarkdown = true;
                this.anotherEditor = 'RichText Mode';
                this.data.EditorContent = "";
                this.data.inMarkdown = true;
            }
        },
        saveContent(){
            alert("存储内容进入数据库:" + this.data.EditorContent)
        },
        postContent(){
            this.$http.post('/addpost', {panelId: this.panelId, userId: this.data.id, content: this.data.EditorContent, type:"html"})
                .then((res) => {
                    alert("Add a post!");
                    window.location.reload()
                })
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

