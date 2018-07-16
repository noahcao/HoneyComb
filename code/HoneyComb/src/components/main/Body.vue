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
    <img src="../../../static/pic/蜜蜂数据.png" alt="80x80" class="img-rounded">
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
      <div class="panel-body pull-right">
        Copyright © 2018 Honeycomb. All Rights Reserved
      </div>
    </div>
    <!-- /input-group -->

    <!-- /.col-lg-6 -->

  </section>
</template>
<script>
// import Login from '../User/Login'
// import Register from '../User/Register'
/* eslint-disable */
//import a from "../../assets/js/canvas-nest";
export default {
  name: 'main-body',
  data () {
    return {
      status
    }
  },
  mounted () {
    $('canvas').remove()
    if ($(window).width() > 768) {
      !(function () {
        function o (w, v, i) {
          return w.getAttribute(v) || i
        }

        function j (i) {
          return document.getElementsByTagName(i)
        }

        function l () {
          var i = j('script')

          var w = i.length

          var v = i[w - 1]
          return {
            l: w,
            z: o(v, 'zIndex', -1),
            o: o(v, 'opacity', 0.5),
            c: o(v, 'color', '36,41,46'),
            n: o(v, 'count', 99)
          }
        }

        function k () {
          r = u.width = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth, n = u.height = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight
        }

        function b () {
          e.clearRect(0, 0, r, n)
          var w = [f].concat(t)
          var x, v, A, B, z, y
          t.forEach(function (i) {
            i.x += i.xa, i.y += i.ya, i.xa *= i.x > r || i.x < 0 ? -1 : 1, i.ya *= i.y > n || i.y < 0 ? -1 : 1, e.fillRect(i.x - 0.5, i.y - 0.5, 1, 1)
            for (v = 0; v < w.length; v++) {
              x = w[v]
              if (i !== x && x.x !== null && x.y !== null) {
                B = i.x - x.x, z = i.y - x.y, y = B * B + z * z
                y < x.max && (x === f && y >= x.max / 2 && (i.x -= 0.03 * B, i.y -= 0.03 * z), A = (x.max - y) / x.max, e.beginPath(), e.lineWidth = A / 2, e.strokeStyle = 'rgba(0,0,0,1)', e.moveTo(i.x, i.y), e.lineTo(x.x, x.y), e.stroke())
              }
            }
            w.splice(w.indexOf(i), 1)
          }), m(b)
        }
        var u = document.createElement('canvas')

        var s = l()

        var c = 'c_n' + s.l

        var e = u.getContext('2d')

        var r; var n; var m = window.requestAnimationFrame || window.webkitRequestAnimationFrame || window.mozRequestAnimationFrame || window.oRequestAnimationFrame || window.msRequestAnimationFrame || function (i) {
          window.setTimeout(i, 1000 / 45)
        }

        var a = Math.random

        var f = {
          x: null,
          y: null,
          max: 20000
        }
        u.id = c
        u.style.cssText = 'position:fixed;top:0;left:0;z-index:' + s.z + ';opacity:' + s.o
        j('body')[0].appendChild(u)
        k(), window.onresize = k
        // window.onmousemove = function (i) {
        //   i = i || window.event, f.x = i.clientX, f.y = i.clientY
        // }, window.onmouseout = function () {
        //   f.x = null, f.y = null
        // };
        for (var t = [], p = 0; s.n > p; p++) {
          var h = a() * r

          var g = a() * n

          var q = 2 * a() - 1

          var d = 2 * a() - 1
          t.push({
            x: h,
            y: g,
            xa: q,
            ya: d,
            max: 6000
          })
        }
        setTimeout(function () {
          b()
        }, 100)
      }())
    }
  }
}
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
@media (min-width: 768px) {
  .panel-default {
    margin-left: 60%;
  }
}
.panel-default {
    background-color: rgba(255, 255, 255, 0);
    border-color: rgba(255, 255, 255, 0);
    position: fixed;
    bottom: 0px;
    height: 70px;
    box-shadow: none;
  }
.input-group-btn:last-child > .btn,
.input-group-btn:last-child > .btn-group {
  z-index: 2;
  margin-left: 0px;
}
</style>
