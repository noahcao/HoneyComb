<template>
  <div>
    <div class="global_toolbar">
      <div class="toolbar_btn center" style="top: 50%; margin-top: -150px;">
        <a href="#" id="needtohelp_0_GetPricing" class="bar_forum bar_project_consulting">
          <samp>
            <i class="iconi icon-LiveChat1">
              <img src="./img/icon/car-0.png" alt="">
            </i>
          </samp>
          <em>
            <i>项目咨询</i>
          </em>
        </a>
        <a id="needtohelp_0_shoppingCart" class="bar_cart " data-id="global_toolbar">
          <samp>
            <i class="iconi icon-LiveChat1">
              <img src="./img/icon/car-1.png" alt="">
            </i>
          </samp>
          <em>
            <i>购物车</i>
          </em>
          <span id="lmliCount" style="display: block;">5</span>
        </a>
        <a href="#" id="needtohelp_0_forum" class="bar_forum">
          <samp>
            <i class="iconi icon-LiveChat1">
              <img src="./img/icon/car-2.png" alt="">
            </i>
          </samp>
          <em>
            <i>查找经销商</i>
          </em>
        </a>
        <a href="#" id="needtohelp_0_GetQuote" class="bar_forum bar_inquire">
          <samp>
            <i class="iconi icon-LiveChat1">
              <img src="./img/icon/car-3.png" alt="">
            </i>
          </samp>
          <em>
            <i>售前咨询</i>
          </em>
        </a>
        <a href="#" id="needtohelp_0_contactus" class="bar_contact bar_forum" data-id="contact_column">
          <samp>
            <i class="iconi icon-LiveChat1">
              <img src="./img/icon/car-4.png" alt="">
            </i>
          </samp>
          <em>
            <i>更多联系方式</i>
          </em>
        </a>
      </div>
      <div class="toolbar_content">
        <div class="css_column cart_column js_column" id="shop_cart">
          <h4>
            购物车
            <a href="javascript:void(0)" class="icon-close ">X</a>
          </h4>
          <div id="resultData">
            <ul style="height: auto;">
              <li class="clearfix" data-url="7002728">
                <input type="checkbox" class="checkbox_c " name="checkbox_c_Name" data-url="" checked="checked">
                <span class="check"></span>
                <div class="img_con">
                  <img src="./img/pd/pd1.jpg" alt="">
                </div>
                <div class="product_name">
                  <span title="CE12808">CE12808</span>
                  <a href="javascript:void(0)" class="del_pro_btn" onClick="">删除</a>
                </div>
                <div class="amount_btn clearfix">
                  <input type="text" value="1" class="spinnerExample" onBlur="isDigit(this)">
                </div>
              </li>
              <li class="clearfix" data-url="7002720">
                <input type="checkbox" class="checkbox_c" name="checkbox_c_Name" data-url="7002720|" checked="checked">
                <span class="check"></span>
                <div class="img_con">
                  <img src="./img/pd/pd2.jpg" alt="">
                </div>
                <div class="product_name">
                  <span title="CE12804">CE12804</span>
                  <a href="javascript:void(0)" class="del_pro_btn" onClick="">删除</a>
                </div>
                <div class="amount_btn clearfix">
                  <input type="text" value="9" class="spinnerExample" onBlur="isDigit(this)">
                </div>
              </li>
              <li class="clearfix" data-url="7097193">
                <input type="checkbox" class="checkbox_c" name="checkbox_c_Name" data-url="7097193|" checked="checked">
                <span class="check"></span>
                <div class="img_con">
                  <img src="./img/pd/pd2.jpg" alt="">
                </div>
                <div class="product_name">
                  <span title="AR1220EV">AR1220EV</span>
                  <a href="javascript:void(0)" class="del_pro_btn" onClick="">删除</a>
                </div>
                <div class="amount_btn clearfix">
                  <input type="text" value="1" class="spinnerExample" onBlur="isDigit(this)">
                </div>
              </li>
              <li class="clearfix" data-url="7115213">
                <input type="checkbox" class="checkbox_c" name="checkbox_c_Name" data-url="7115213|" checked="checked">
                <span class="check"></span>
                <div class="img_con">
                  <img src="./img/pd/pd1.jpg" alt="">
                </div>
                <div class="product_name">
                  <span title="S5700-28P-PWR-LI-AC">S5700-28...</span>
                  <a href="javascript:void(0)" class="del_pro_btn" onClick="">删除</a>
                </div>
                <div class="amount_btn clearfix">
                  <input type="text" value="1" class="spinnerExample" onBlur="isDigit(this)">
                </div>
              </li>
            </ul>
          </div>
          <div class="count_info clearfix">
            <label>
              <input type="checkbox" class="select_all" checked="checkbox">
              <span class="check"></span>
              <span class="text">全选</span>
            </label>
            <p>
              共计：
              <span>12</span>
              个产品
            </p>
          </div>
          <div class="more shop-more clearfix">
            <a href="#" target="">购买更多产品</a>
          </div>
          <div class="shop_function_btn clearfix">
            <a href="#" class="more redbtn-moddle1" id="btn_popup_login">
              <span>登录</span>
            </a>
            <a href="#" class="go_btn more redbtn-moddle1">
              <span>寻求报价</span>
            </a>
          </div>
        </div>
      </div>
    </div>
    <div style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';">
      <p>适用浏览器：IE8、360、FireFox、Chrome、Safari、Opera、傲游、搜狗、世界之窗.</p>
      <p>来源：
        <a href="http://sc.chinaz.com/" target="_blank">站长素材</a>
      </p>
    </div>
  </div>
</template>

<script>
import a from '../../assets/js/common'
// import b from '../../assets/js/jquery-1.11.1.min'
export default {
  name: 'side-bar'
}
</script>

<style scoped>
::-webkit-scrollbar {
  display: none;
}

::-moz-placeholder {
  color: #aaa;
}

::-webkit-input-placeholder {
  color: #aaa;
}

:-ms-input-placeholder {
  color: #aaa;
}

::selection {
  background: #111;
  color: #fff;
  font-weight: bold;
}

html {
  color: #333;
}

body {
  min-width: 1200px;
}

html,
body {
  margin: 0;
  height: 100%;
  font-family: "Myriad Set Pro", "Helvetica Neue", Helvetica, Arial, Verdana,
    sans-serif;
}

* {
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
}

a {
  text-decoration: none;
  color: #000;
}

hr {
  height: 1px;
  background: #ccc;
  width: 100%;
  border: none;
  margin: 0;
  padding: 0;
  margin-top: 10px;
}

img {
  border: 0;
}

body {
  background: #eee;
  color: #666;
  font: 12px/150% Arial, Verdana, "microsoft yahei";
}

html,
body,
div,
dl,
dt,
dd,
ol,
ul,
li,
h1,
h2,
h3,
h4,
h5,
h6,
p,
blockquote,
pre,
button,
fieldset,
form,
input,
legend,
textarea,
th,
td {
  margin: 0;
  padding: 0;
}

article,
aside,
details,
figcaption,
figure,
footer,
header,
main,
menu,
nav,
section,
summary {
  display: block;
  margin: 0;
  padding: 0;
}

audio,
canvas,
progress,
video {
  display: inline-block;
  vertical-align: baseline;
}

a {
  text-decoration: none;
  color: #08acee;
}

a:active,
a:hover {
  outline: 0;
}

button {
  outline: 0;
}

mark {
  color: #000;
  background: #ff0;
}

small {
  font-size: 80%;
}

img {
  border: 0;
}

button,
input,
optgroup,
select,
textarea {
  margin: 0;
  font: inherit;
  color: inherit;
  outline: none;
}

li {
  list-style: none;
}

i {
  font-style: normal;
}

a {
  color: #666;
}

a:hover {
  color: #eee;
}

em {
  font-style: normal;
}

h2,
h3 {
  font-family: "microsoft yahei";
  font-weight: 100;
}
.cistern:after,
.clearfix:after {
  display: table;
  clear: both;
  content: " ";
}

.more.shop-more {
  float: none;
  padding: 20px;
  text-align: center;
  border-top: 1px solid #e6e6e6;
}

.amount_btn {
  float: right;
  width: 71px;
  margin-top: -5px;
}

.amount_btn a {
  float: left;
  width: 15px;
  height: 15px;
  background: #fff;
}

.amount_btn input {
  font-size: 12px;
  line-height: 12px;
  float: left;
  width: 35px;
  height: 15px;
  text-align: center;
  color: #666;
  border: none;
  border-top: 1px solid #ddd;
  border-bottom: 1px solid #ddd;
}

.count_info {
  font-size: 12px;
  position: relative;
  margin: 20px;
}

.count_info label {
  font-size: 14px;
  float: left;
  width: 70px;
  color: #666;
}

.count_info label input {
  width: 15px;
  margin-right: 10px;
  cursor: pointer;
}

.count_info label span.text {
  display: inline-block;
  cursor: pointer;
}

.count_info label input:checked + span {
  background-image: url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAFoAAABaCAYAAAA4qEECAAAFUElEQVR4Xu3cT2gUVxwH8O9vdmeaWiFUkDbpzlaxyWYVvHjon5RQqCBVsB4Ei1A86Sne7Km0paU3e9OTQsEKLQELeqggWijBFCl4KdTNJi21u0tSCFSCrYaZnfmV2Y3tZv/E7O68X0L2l2vmve97H3/73szsizSddhj6Y1yAFNq4cSVAoWWcFVrIWaEVWkpAKEfXaIUWEhCK0YpWaCEBoRitaIUWEhCK0YpWaCEBoRitaIUWEhCK0YpWaCEBoRitaIUWEhCK0YpWaCEBoRitaIUWEhCK0YpWaCGBFTG8CFC/iWSt6P9Vc8/CG33MzpcgHIkbW6EBMGM26XujQ39igYFkPu1cA3AwTmyFBu6H5L2x+w/MP4H9BXAs175JRGNxYfc0NAPzdtl79ZU5FGtBFTqu8or6YV6w2R/dVcJsbbe6dMSIzMx/Jcr+m8PzyNUhJ/Kuc0U3w1iweREhjY2UvJ/rkK28a38NomOxxNR10lNrNDM/AmEsW/Dv1mNOp+2vAHrfBHLUZw9B85LFfGC4WJ6sx8y59jkiGjeF3DPQDPYS4EPDhfKthkp2nbMgnDGJ3BvQzAFxeDhTCq43Iic/BFmfm0be/NDMIYGOZ4reRMNykbLHyaJzEsibG5qZiXAiU/Av12PmU/ZJtuiCFPKmhqaQT2VK/sUGZNc5xuBvQEQK3aUAh3w6W/LPNyInjjCsKyBKdBnRdvNNd3tHYfhJplT+rHG5SBwMybpGRMm2lWJoYBDa3Ev0lvNmfDFS9D6o//2MmxwLiG4SyInBrKMuzEAzT1Dgj3PSiR4Osh2NrM1GDL6YLfin6pvNvmS/VrbwPRFtabPLWC83AX09U/AOExDMvojtZduZIsJQrKNu6IwvZwr+CQJW/F17Lm3vA+MHItpqNv/pvccKzeBbYcE/tAfwnkTfexkDFjs/Atjx9OF0cAXzRKboHycgrG09nXL2wuJJU98BtjvSeKGZJ/sS/oGd97FUO5BfB+GWE84UCG67A1z1esbVTNE7Gn16aq/7LYUhj+w7RLQt1rwuOosVOhpHVNUjBf8dAsorKuwF7ORnnCkCBroY739NpXLiGGvUR+zQlYGtUmk+2VMg2t7NBFj6k9PNYJfbmoGuYDdfO2cGkA2S9u1OP9bMfKc/8N8enMOj2vlHG2/gOD8Z2wu6xDYHXS3tpncDnW5UDL4bPvbf2rOAv2vnXUxh2z+Wc1vqVrITc8PQlTW76f1tB7deOfuh9/quB1hcsfE9j35/qz0Jor2dAEi1MQ5dXUX4fLbon66f1FofJmoPuNT2MTeILYtJe5JA+6TAOs0RgV7eIFs+HodENwDqazGJhgMu0XW/70DfUmDfiPOQS6eIa2knB10p7fCjkWK54RuNmXRyfwD6rv5dxKoHXNJ2dP3+tUxyI1wjC12xbvEKM1X3dk34gIvpfwxx6MrNe8uX8tX3xQwstjjgYuVd51sTB1w2JXS0O7b8msl13mPGvSYHXCifti+ZPHthEntdKrq6Obb+4rTZhHNp+wKBTprEMNn3+kFXsQNCeDRTDK6uNkmJAy4mkSvL5Xr/l5nMXLY4fLfZuYtogLm08ykBH5uGMN3/ukNXCrvFSaKcmzxDZJ01jSDR/4aArk505dm49Th7YRJ8A0FXHtUrpz0tYDczLkmfvegZ6OXKfgjGcyCyTE5cuu8NVdHSk5fMU2ghbYVWaCEBoRitaIUWEhCK0YpWaCEBoRitaIUWEhCK0YpWaCEBoRitaIUWEhCK0YpWaCEBoRitaIUWEhCK0YpWaCEBoRitaIUWEhCK0YpWaCEBoRitaCHofwGXQYe31xGynQAAAABJRU5ErkJggg==");
  background-size: 18px;
  background-repeat: no-repeat;
  background-position: center;
  width: 18px;
  height: 18px;
}

.count_info label span.check {
  position: absolute;
  top: 3px;
  left: 0;
  display: none\9;
  background: #fff;
  background-image: url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAFoAAABaCAYAAAA4qEECAAACyklEQVR4Xu3dwWoTURQG4HPCIIMgEUG0RREXaZGoIdz7BoK4FXwBXbsSXLjTnbj1AXwDF+7qK3jnQhbRLA1WcRcVgwOGOTLBKW0ohrb2Tzr5Z9lm7n/Pl59sjwofiIDuTun3+6fyPL8vIrfNrKuqVyG3qFfIyMxeN5vNR61W60c12g50jPFKURRbqrpZr7kXNs3QzO547wflDabQf5v8TkRuLuxa9Qwepml6vd1u/5xCZ1n2WERe1HPWxU5lZs+9908q6Pcicm2xV6pnuplte+8vq5klMcbf9RxzOaYys/MaQlhT1S+zVzKz7977s8tx1ZNxiyzLPovI+uxtG43GhsYY182s/MDsM3LOnTsZIy7HLUMIn1T1EqGP+fsg9DEDV8cTmtAgAVAMG01okAAoho0mNEgAFMNGExokAIphowkNEgDFsNGEBgmAYthoQoMEQDFsNKFBAqAYNprQIAFQDBtNaJAAKIaNJjRIABTDRhMaJACKYaMJDRIAxbDRhAYJgGLYaEKDBEAxbDShQQKgGDaa0CABUAwbTWiQACiGjSY0SAAUw0YTGiQAimGjCQ0SAMWw0YQGCYBi2GhCgwRAMWw0oUECoBg2mtAgAVAMG01okAAoho0mNEgAFMNGExokAIphowkNEgDFsNGEBgmAYthoQoMEQDFsNKFBAqAYNprQIAFQDBtNaJAAKIaNJjRIABTDRi8zNFc4HfzbCSF8VdULs29OVzgNBoMz4/F4Z5PkwY/nG/ME0jRdq9bs7btMa94B/P98gfKXodw5VkG/FJGH81/jJw4h8Mo596BahXoxz/MPIsK1eoeQ/Mcr35IkudHpdLZ3L/e9VRTFG1U9/X+zVvM0M/ulqnedc1ulwJ511b1eb3MymTw1s3uqmqwm0ZGnHonIWxF55pwrfyWmzx7o6o/lMklV3Thy5AodYGamqsNut/txv7H/AMxH7+5pNskrAAAAAElFTkSuQmCC");
  background-size: 18px;
  background-repeat: no-repeat;
  background-position: center;
  width: 18px;
  height: 18px;
  cursor: pointer;
}

.count_info p {
  font-size: 14px;
  float: right;
  width: 70%;
  margin: 0;
  text-align: right;
  color: #666;
}

.shop_function_btn {
  padding: 20px 0;
  text-align: center;
  border-top: 1px solid #e6e6e6;
  border-bottom: 1px solid #e6e6e6;
}

.shop_function_btn a {
  margin: 0 10px;
}

div.more {
  line-height: 1.1;
  float: right;
  clear: both;
  margin-top: 12px;
  padding-left: 0 !important;
}

.more-resources .more a,
.more a {
  display: inline-block;
  padding-left: 20px;
  color: #999;
}

.more-resources label {
  display: inline-block;
  margin-bottom: 12px;
  -webkit-transition: all 0.4s cubic-bezier(0.2, 0.83, 0.42, 0.91);
  transition: all 0.4s cubic-bezier(0.2, 0.83, 0.42, 0.91);
  vertical-align: top;
}

.more a:hover {
  text-decoration: underline;
  color: #333;
}

.more a em,
.more a i {
  font-size: 24px;
  font-style: normal;
  display: inline-block;
  width: 36px;
  height: 23px;
  margin-left: -12px;
  color: #e50000;
  -webkit-font-smoothing: antialiased;
}

.more a em:before,
.more a i:before {
  position: relative;
  top: 3px;
}

.cloud_computing div.more {
  float: none;
}

.global_toolbar.default {
  position: absolute;
}

.global_toolbar.default .toolbar_content,
.global_toolbar.opacity .toolbar_content {
  display: none;
}

.global_toolbar.opacity .toolbar_btn a em,
.global_toolbar.opacity .toolbar_btn a samp {
  background: #000;
  background: rgba(0, 0, 0, 0.8);
  cursor: pointer;
}

.global_toolbar.opacity .toolbar_btn a:hover em {
  background: #e50000;
}

.global_toolbar {
  position: fixed;
  z-index: 1001;
  top: 0;
  right: 0;
  width: 0;
  height: 100%;
  -webkit-transition: all 0.4s cubic-bezier(0.2, 0.83, 0.42, 0.91);
  transition: all 0.4s cubic-bezier(0.2, 0.83, 0.42, 0.91);
  border-left: 1px solid #d0d0d0;
  background: #fff;
}

.global_toolbar.open {
  width: 320px;
}

.wap_cartbtn {
  display: none;
}

.toolbar_btn {
  position: absolute;
  top: 100%;
  left: -60px;
  width: 60px;
}

.toolbar_btn a,
.toolbar_btn a samp {
  position: relative;
  display: block;
  height: 60px;
  -webkit-transition: all 0.4s cubic-bezier(0.2, 0.83, 0.42, 0.91);
  transition: all 0.4s cubic-bezier(0.2, 0.83, 0.42, 0.91);
  border-bottom: 1px solid #37393d;
}

.toolbar_btn a samp {
  z-index: 2;
  width: 60px;
  color: #fff;
  background-color: #000;
}

.toolbar_btn a samp .iconfont {
  font-size: 24px;
  position: absolute;
  top: 16px;
  left: 18px;
  color: #fff;
}

.toolbar_btn a em {
  font-size: 16px;
  font-style: normal;
  line-height: 60px;
  position: absolute;
  z-index: 1;
  top: 0;
  right: 60px;
  display: block;
  overflow: hidden;
  width: 0;
  height: 60px;
  -webkit-transition: all 0.4s cubic-bezier(0.2, 0.83, 0.42, 0.91);
  transition: all 0.4s cubic-bezier(0.2, 0.83, 0.42, 0.91);
  text-align: center;
  color: #fff;
  background-color: #000;
}

.LANG-cn .toolbar_btn a font {
  width: 160px;
}

.toolbar_btn a font {
  display: block;
  width: 180px;
  padding: 0 30px;
  text-align: left;
  white-space: nowrap;
  word-break: keep-all;
}

.toolbar_btn a.current samp,
.toolbar_btn a:hover samp {
  border-bottom: 1px solid #e50000;
  background-color: #e50000;
}

.toolbar_btn.default a:hover samp {
  background-color: #e50000;
}

.LANG-cn .toolbar_btn a:hover em {
  width: 160px;
}

.toolbar_btn a:hover em {
  width: 180px;
  background: #e50000;
}

.toolbar_btn a span {
  font-size: 10px;
  line-height: 11px;
  position: absolute;
  z-index: 3;
  top: 10px;
  right: 10px;
  display: none;
  overflow: hidden;
  min-width: 11px;
  height: 14px;
  padding: 1px 2px;
  text-align: center;
  color: #fff;
  border: 1px solid #e50000;
  border-radius: 10px;
  background: #e50000;
}

.toolbar_btn a.current span,
.toolbar_btn a:hover span {
  color: #e50000;
  background: #fff;
}

.LANG-cn .toolbar_btn.default em {
  width: 160px;
}

.toolbar_btn.default em {
  width: 180px;
}

.toolbar_btn.default a samp,
.toolbar_btn.default em {
  border-bottom: 1px solid #37393d;
  background: #000;
}

.toolbar_content {
  z-index: 3;
  width: 100%;
}

.css_column,
.toolbar_content {
  position: absolute;
  top: 0;
  left: 0;
  height: 100%;
}

.css_column {
  z-index: 1000;
  overflow: hidden;
  width: 320px;
  min-width: 280px;
  background: #fafafa;
}

.css_column h4 {
  font-size: 24px;
  position: relative;
  margin: 0;
  padding: 15px 20px;
  color: #666;
  border-bottom: 1px solid #e6e6e6;
  background: #fff;
  font-weight: normal;
}

.css_column h4 a {
  font-size: 20px;
  position: absolute;
  top: 19px;
  right: 10px;
  display: block;
  width: 20px;
  height: 20px;
  color: #bfbfbf;
}

.css_column h4 a:hover {
  color: #e50000;
}

.cart_column ul {
  overflow: auto;
  padding: 0 20px;
  border-bottom: 1px solid #e6e6e6;
}

#resultData {
  overflow: auto;
}

.cart_column li {
  position: relative;
  padding: 20px 0;
  border-bottom: 1px solid #e6e6e6;
}

.cart_column li:last-child {
  border: none;
}

.cart_column li .img_con {
  float: left;
  width: 70px;
  margin: 0 10px 0 38px;
  text-align: center;
}

.cart_column li .checkbox_c {
  float: left;
  width: 15px;
  margin-top: 20px;
  position: absolute;
  z-index: 2;
  opacity: 0;
  left: 0;
  cursor: pointer;
}

.cart_column li .checkbox_c:checked + span {
  background: #fff;
}

.cart_column li .check {
  position: absolute;
  top: 36px;
  left: 0;
  display: none\9;
  width: 20px;
  height: 20px;
}

.cart_column li img {
  width: 100%;
}

.cart_column li .product_name {
  font-size: 12px;
  line-height: 16px;
  float: left;
  width: 100px;
  margin-right: 10px;
}

.cart_column li .product_name span {
  font-size: 16px;
  display: block;
  margin-bottom: 3px;
  padding-top: 8px;
  cursor: pointer;
  color: #666;
}

.cart_column li .product_name a {
  font-size: 14px;
  line-height: 16px;
  color: #999;
}

.cart_column li .product_name a:hover {
  color: #0166fe;
}

.compare_column ul {
  margin-bottom: 20px;
}

.compare_column li .img_con {
  margin: 0 10px 0 0;
}

.compare_column li.null {
  min-height: 73px;
  padding: 20px 0;
}

.contact_column ul {
  margin-top: 30px;
}

.contact_column li {
  margin-bottom: 20px;
}

.contact_column li a {
  display: block;
  min-height: 50px;
  text-decoration: none;
}

.contact_column li a:hover img {
  opacity: 0.8;
}

.contact_column li h3 {
  font-size: 16px;
  font-weight: 400;
  line-height: 1.5;
  margin: 0 0 8px;
  color: #333;
}

.contact_column li p {
  font-size: 12px;
  line-height: 14px;
  margin: 0;
  color: #666;
}

.contact_column li a:hover h3,
.contact_column li a:hover p {
  color: #0166fe;
}

.contact_tel {
  padding: 14px 0;
  border-top: 1px solid #d7d7d7;
  border-bottom: 1px solid #d7d7d7;
}

.contact_tel span {
  font-size: 24px;
  line-height: 50px;
  display: block;
  min-height: 50px;
  color: #787878;
}

.else_contact {
  margin: 10px 0 0;
  text-align: right;
}

.else_contact a {
  font-size: 14px;
  color: #333;
}

.else_contact a:hover {
  color: #0166fe;
}

.listimg_wrap {
  float: left;
  width: 18%;
}

.redbtn-moddle1 {
  font-size: 14px;
  line-height: 1.2;
  position: relative;
  display: inline-block;
  overflow: hidden;
  padding: 16px 30px;
  -webkit-transition: all 0.5s;
  transition: all 0.5s;
  text-transform: capitalize;
  color: #fff;
  background: #e50000;
}

.redbtn-moddle1:hover {
  color: #fff;
}

.redbtn-moddle1:before {
  position: absolute;
  z-index: 0;
  top: 0;
  left: -100%;
  display: block;
  width: 100%;
  height: 100%;
  content: "";
  -webkit-transition: all 0.4s ease-in-out;
  transition: all 0.4s ease-in-out;
  background: #b20000;
}

.redbtn-moddle1:hover:before {
  left: 0;
}

.redbtn-moddle1 span {
  font-size: 16px;
  position: relative;
  z-index: 1;
  letter-spacing: 1px;
}

.cart_column li .check {
  position: absolute;
  top: 36px;
  left: 0;
  display: none\9;
  background-image: url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAFoAAABaCAYAAAA4qEECAAACyklEQVR4Xu3dwWoTURQG4HPCIIMgEUG0RREXaZGoIdz7BoK4FXwBXbsSXLjTnbj1AXwDF+7qK3jnQhbRLA1WcRcVgwOGOTLBKW0ohrb2Tzr5Z9lm7n/Pl59sjwofiIDuTun3+6fyPL8vIrfNrKuqVyG3qFfIyMxeN5vNR61W60c12g50jPFKURRbqrpZr7kXNs3QzO547wflDabQf5v8TkRuLuxa9Qwepml6vd1u/5xCZ1n2WERe1HPWxU5lZs+9908q6Pcicm2xV6pnuplte+8vq5klMcbf9RxzOaYys/MaQlhT1S+zVzKz7977s8tx1ZNxiyzLPovI+uxtG43GhsYY182s/MDsM3LOnTsZIy7HLUMIn1T1EqGP+fsg9DEDV8cTmtAgAVAMG01okAAoho0mNEgAFMNGExokAIphowkNEgDFsNGEBgmAYthoQoMEQDFsNKFBAqAYNprQIAFQDBtNaJAAKIaNJjRIABTDRhMaJACKYaMJDRIAxbDRhAYJgGLYaEKDBEAxbDShQQKgGDaa0CABUAwbTWiQACiGjSY0SAAUw0YTGiQAimGjCQ0SAMWw0YQGCYBi2GhCgwRAMWw0oUECoBg2mtAgAVAMG01okAAoho0mNEgAFMNGExokAIphowkNEgDFsNGEBgmAYthoQoMEQDFsNKFBAqAYNprQIAFQDBtNaJAAKIaNJjRIABTDRi8zNFc4HfzbCSF8VdULs29OVzgNBoMz4/F4Z5PkwY/nG/ME0jRdq9bs7btMa94B/P98gfKXodw5VkG/FJGH81/jJw4h8Mo596BahXoxz/MPIsK1eoeQ/Mcr35IkudHpdLZ3L/e9VRTFG1U9/X+zVvM0M/ulqnedc1ulwJ511b1eb3MymTw1s3uqmqwm0ZGnHonIWxF55pwrfyWmzx7o6o/lMklV3Thy5AodYGamqsNut/txv7H/AMxH7+5pNskrAAAAAElFTkSuQmCC");
  background-size: 18px;
  background-repeat: no-repeat;
  background-position: center;
  width: 18px;
  height: 18px;
}

.cart_column li .checkbox_c:checked + span {
  background-image: url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAFoAAABaCAYAAAA4qEECAAAFUElEQVR4Xu3cT2gUVxwH8O9vdmeaWiFUkDbpzlaxyWYVvHjon5RQqCBVsB4Ei1A86Sne7Km0paU3e9OTQsEKLQELeqggWijBFCl4KdTNJi21u0tSCFSCrYaZnfmV2Y3tZv/E7O68X0L2l2vmve97H3/73szsizSddhj6Y1yAFNq4cSVAoWWcFVrIWaEVWkpAKEfXaIUWEhCK0YpWaCEBoRitaIUWEhCK0YpWaCEBoRitaIUWEhCK0YpWaCEBoRitaIUWEhCK0YpWaCEBoRitaIUWEhCK0YpWaCGBFTG8CFC/iWSt6P9Vc8/CG33MzpcgHIkbW6EBMGM26XujQ39igYFkPu1cA3AwTmyFBu6H5L2x+w/MP4H9BXAs175JRGNxYfc0NAPzdtl79ZU5FGtBFTqu8or6YV6w2R/dVcJsbbe6dMSIzMx/Jcr+m8PzyNUhJ/Kuc0U3w1iweREhjY2UvJ/rkK28a38NomOxxNR10lNrNDM/AmEsW/Dv1mNOp+2vAHrfBHLUZw9B85LFfGC4WJ6sx8y59jkiGjeF3DPQDPYS4EPDhfKthkp2nbMgnDGJ3BvQzAFxeDhTCq43Iic/BFmfm0be/NDMIYGOZ4reRMNykbLHyaJzEsibG5qZiXAiU/Av12PmU/ZJtuiCFPKmhqaQT2VK/sUGZNc5xuBvQEQK3aUAh3w6W/LPNyInjjCsKyBKdBnRdvNNd3tHYfhJplT+rHG5SBwMybpGRMm2lWJoYBDa3Ev0lvNmfDFS9D6o//2MmxwLiG4SyInBrKMuzEAzT1Dgj3PSiR4Osh2NrM1GDL6YLfin6pvNvmS/VrbwPRFtabPLWC83AX09U/AOExDMvojtZduZIsJQrKNu6IwvZwr+CQJW/F17Lm3vA+MHItpqNv/pvccKzeBbYcE/tAfwnkTfexkDFjs/Atjx9OF0cAXzRKboHycgrG09nXL2wuJJU98BtjvSeKGZJ/sS/oGd97FUO5BfB+GWE84UCG67A1z1esbVTNE7Gn16aq/7LYUhj+w7RLQt1rwuOosVOhpHVNUjBf8dAsorKuwF7ORnnCkCBroY739NpXLiGGvUR+zQlYGtUmk+2VMg2t7NBFj6k9PNYJfbmoGuYDdfO2cGkA2S9u1OP9bMfKc/8N8enMOj2vlHG2/gOD8Z2wu6xDYHXS3tpncDnW5UDL4bPvbf2rOAv2vnXUxh2z+Wc1vqVrITc8PQlTW76f1tB7deOfuh9/quB1hcsfE9j35/qz0Jor2dAEi1MQ5dXUX4fLbon66f1FofJmoPuNT2MTeILYtJe5JA+6TAOs0RgV7eIFs+HodENwDqazGJhgMu0XW/70DfUmDfiPOQS6eIa2knB10p7fCjkWK54RuNmXRyfwD6rv5dxKoHXNJ2dP3+tUxyI1wjC12xbvEKM1X3dk34gIvpfwxx6MrNe8uX8tX3xQwstjjgYuVd51sTB1w2JXS0O7b8msl13mPGvSYHXCifti+ZPHthEntdKrq6Obb+4rTZhHNp+wKBTprEMNn3+kFXsQNCeDRTDK6uNkmJAy4mkSvL5Xr/l5nMXLY4fLfZuYtogLm08ykBH5uGMN3/ukNXCrvFSaKcmzxDZJ01jSDR/4aArk505dm49Th7YRJ8A0FXHtUrpz0tYDczLkmfvegZ6OXKfgjGcyCyTE5cuu8NVdHSk5fMU2ghbYVWaCEBoRitaIUWEhCK0YpWaCEBoRitaIUWEhCK0YpWaCEBoRitaIUWEhCK0YpWaCEBoRitaIUWEhCK0YpWaCEBoRitaIUWEhCK0YpWaCEBoRitaCHofwGXQYe31xGynQAAAABJRU5ErkJggg==");
  background-size: 18px;
  background-repeat: no-repeat;
  background-position: center;
  width: 18px;
  height: 18px;
}

:root .popup_login {
  background-color: rgba(0, 0, 0, 0.5);
}

.login_form {
  background: #fbfcfc;
}

.login_form {
  width: 60%;
  float: left;
  background: #fff;
  border-top-right-radius: 5px;
  border-bottom-right-radius: 5px;
  padding: 50px 77px 120px;
  position: relative;
}

.popup_login {
  position: fixed;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  z-index: 1005;
  background: url("/themes/img/icon/alpha60.png") repeat;
}

.login_form h3 {
  font-size: 26px;
  margin-bottom: 30px;
  font-weight: normal;
}

.popup_login .login_form {
  padding-bottom: 40px;
  z-index: 1006;
  margin: 0 auto;
  float: none;
  width: 480px;
  border-radius: 0;
  position: fixed;
  left: 50%;
  margin-left: -240px;
  top: 50%;
  margin-top: -200px;
  padding-left: 38px;
  padding-right: 38px;
}

.close_popup {
  display: block;
  width: 22px;
  height: 22px;
  position: absolute;
  top: 15px;
  right: 20px;
}

.close_popup img {
  width: 100%;
  height: auto;
  display: block;
  border: none;
}

.form_block {
  border: 1px #d4d4d4 solid;
  height: 48px;
  padding-left: 50px;
  position: relative;
  margin-bottom: 18px;
}

.input_icon {
  display: block;
  position: absolute;
  width: 16px;
  height: 16px;
  left: 15px;
  top: 16px;
}

.username_icon img {
  width: 100%;
  height: auto;
  display: block;
  border: none;
}

.password_icon img {
  width: 100%;
  height: auto;
  display: block;
  border: none;
}

.form_block input {
  border: none;
  height: 46px;
  line-height: 46px;
  font-size: 14px;
  width: 100%;
  padding: 0 10px 0 0;
  -webkit-outline: none;
  outline: none;
}

.form_login_btn {
  width: 100%;
  height: 50px;
  line-height: 43px;
  text-align: center;
  border: none;
  background: #ff3c5b;
  color: #fff;
  font-size: 18px;
}

.form_login_btn:hover {
  background: #d70226;
}

.form_login_btn.disabled {
  background: #ccc;
  color: #e7e7e7;
}

.login_else_info {
  margin-top: 10px;
}

.login_else_info span {
  float: left;
  font-size: 14px;
}

.login_else_info span label {
  display: inline-block;
  margin: 0 10px;
  color: #adadad;
}

.login_else_info span a {
  color: #333;
}

.need_reg {
  float: right;
  font-size: 12px;
  color: #666;
}

.need_reg a {
  font-size: 14px;
  color: #ff3c5b;
  font-weight: bold;
}

.login_else_info a:hover {
  color: #0166fe;
}

.tips_error {
  text-align: center;
  color: #e50000;
  font-size: 14px;
  margin-bottom: 15px;
}

.toolbar_btn a em i {
  font-style: normal;
}

.iconi {
  width: 30px;
  height: 30px;
  display: block;
  position: absolute;
  top: 15px;
  left: 15px;
}

.icon-LiveChat1 img {
  width: 100%;
  height: auto;
  display: block;
  border: none;
}

.icon-close {
  position: absolute;
  right: 0;
}

.spinner-sprite,
.spinner .decrease,
.spinner .decrease[disabled],
.spinner .increase,
.spinner .value,
.spinner .value.passive {
  background-image: url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAB9CAYAAAC4TqMRAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyhpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMTM4IDc5LjE1OTgyNCwgMjAxNi8wOS8xNC0wMTowOTowMSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTcgKE1hY2ludG9zaCkiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6NzMwNURCNzM1MjVEMTFFOEE4MkJGRjY3QUFEQThFRkIiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6NzMwNURCNzQ1MjVEMTFFOEE4MkJGRjY3QUFEQThFRkIiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDo3MzA1REI3MTUyNUQxMUU4QTgyQkZGNjdBQURBOEVGQiIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDo3MzA1REI3MjUyNUQxMUU4QTgyQkZGNjdBQURBOEVGQiIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/PlGC+J4AAAIBSURBVHja7FrNjoIwEB60voZ6NerJF5AnJ+HiRWOiUa/G+A7GH3B36tZYAkh3Z1jAaVIJSOfrN9Oh0069IAjuwFR83/fgdDrdOcpsNtMdV/jzfU/eeyOzBcyFHUAhFQ4VNYdBYYDhcKivm82m5ipyVef/MRiPx6kNRqORdb9er3lH0bu2KuuP3W5n3Q8Gg9Tn1+v1dwwul0tqg6znuQxcFHR3fE/9KNJF6U7vFTbydrvV1/P57K6iIsVVsHyuK8RgtVrVd9LHoE4P0263C+12GzzPI4uJ4jiG4/H48INOpwNKKWi1aAih8Nvtpjv8BEAGWKkATGc1AAqmZIA9Ly10VAbRVCoGRhYrANaGqAgYAMCyQQKVhEEjRlEpAOLJbz1ZjNxwIz8BcA7FSlVQlpkyNcBisSDrfXLtpgGiKGLb8dIA0+mUdxTVOroux9HqvQDhZiAqqjiDrF2vZMnb9SJRUV773M0QijV0SX7AbWQQTxYG1Wcwn89rzkACL/EDCbzEyH9gsN/vCwnp9/sVDbx6vZ4EXhJ4CQMJvOAlzcWxpRaG4SPVOJlMdLKOOhO4XC4fDFCwqVTF2tY0SWuq0fQqix2gPEerrYpYAbCKkRtuZCtsIQdIhi3UACDfog/zZJPmojw8YxJ/GgDPK5ozQZSTvgWAD6kPMD2NfDgc2GzwJcAAQtZmLGmu8EcAAAAASUVORK5CYII=");
}

.spinner {
  height: 25px;
  width: 71px;
  overflow: hidden;
  *zoom: 1;
  -webkit-box-shadow: 0 3px 3px -4px #aaa;
  -moz-box-shadow: 0 3px 3px -4px #aaa;
  box-shadow: 0 3px 3px -4px #aaa;
}

.spinner button,
.spinner .value {
  text-align: center;
  display: block;
  float: left;
  height: 100%;
  line-height: 20px;
  margin: 0;
}

.spinner button {
  border: none;
  width: 23px;
  color: #e5312a;
  font: 22px Arial bold;
  padding: 0;
  outline: none;
}

.spinner .decrease {
  background-position: 0 -50px;
  cursor: pointer;
  text-indent: -10000px;
}

.spinner .decrease[disabled] {
  background-position: 0 -75px;
  cursor: default;
}

.spinner .increase {
  background-position: 0 0;
  cursor: pointer;
  text-indent: -10000px;
}

.spinner .value {
  background-position: 0 -100px;
  width: 24px;
  height: 25px;
  border: none;
  color: #000;
  padding: 0;
}

.spinner .value.passive {
  background-position: 0 -25px;
  color: #919191;
}

.spinner .error,
.spinner .invalid {
  background: #aa0000;
}
</style>
