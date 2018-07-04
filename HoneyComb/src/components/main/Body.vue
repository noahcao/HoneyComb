<template>
    <section class="container">
        <!-- <figure class="logo animated fadeInDown delay-07s">
            <a href="#">
                <img src="img/logo_noBG_green.png" alt="">
            </a>
        </figure>
        <h1 class="animated fadeInDown delay-07s">Honeycomb</h1>
        <ul class="we-create animated fadeInUp delay-1s">
            <li>A React prototyping tool.</li>
            <br>
            <li>Generate your React project component hierarchy.</li>
        </ul>
        <a class="link animated fadeInUp delay-1s servicelink" href="https://github.com/ReactPrimer/ReactPrimer">Check Us Out on GitHub</a> -->
        <img src="../../assets/pic/蜜蜂数据.png" alt="80x80" class="img-rounded">
        <h3>An Academic Document Management Project.</h3>
        <div class="input-group input-group-lg">
            <input type="text" class="form-control" placeholder="Search for...">
            <span class="input-group-btn">
                <button class="btn btn-default" type="button">
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                </button>
            </span>
        </div>
        <div class="panel panel-default">
            <div class="panel-body">
                Copyright © 2018 Honeycomb. All Rights Reserved
            </div>
        </div>
        <!-- /input-group -->

        <!-- /.col-lg-6 -->
    </section>
</template>
<script>
import a from "../../assets/js/canvas-nest";
export default {
    name: "main-body"
};
</script>
<style scoped>
.form-control {
    border: none;
    border-radius: 4px;
    color: rgba(255, 255, 255, 0.8);
}
input::-webkit-input-placeholder {
    /* WebKit browsers */
    color: rgba(255, 255, 255, 0.8);
}
input {
    transition: all 1s ease;
    background-color: rgba(36, 41, 46, 0.3);
    outline: none;
    box-shadow: none;
}
input:hover,
input:focus {
    background-color: rgba(36, 41, 46, 0.5);
    border: 1px solid grey;
    box-shadow: none;
}
.container {
    margin-top: 80px;
    text-align: center;
}
.btn-default {
    transition: all 0.3s ease;
    background: rgba(255, 255, 255, 0.5);
    border-color: rgba(36, 41, 46, 0);
}
.input-group-btn > .btn:active,
.input-group-btn > .btn:focus {
    z-index: 2;
    box-shadow: none;
    outline: none;
}
.btn-default:active:focus {
    color: #333;
    background-color: #d4d4d4;
    border-color: rgba(36, 41, 46, 0);
}
.btn-default:hover {
    color: #333;
    background-color: rgba(255, 255, 255, 0.9);
    border-color: rgba(36, 41, 46, 0);
}

.input-group {
    margin-left: 20%;
    margin-right: 20%;
    margin-top: 50px;
}
.img-rounded {
    display: inline-block;
}
.panel-default{
    background-color: rgba(255, 255, 255, 0);
    border-color: rgba(255, 255, 255, 0);
    position:fixed;
    bottom:0px;
    height: 70px;
    margin-left: 60%;
    box-shadow: none;
}
</style>
