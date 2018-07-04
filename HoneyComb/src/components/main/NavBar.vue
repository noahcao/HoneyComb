<template>
  <nav class="navbar navbar-inverse">
    <div class="container-fluid">
      <!-- Brand and toggle get grouped for better mobile display -->
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#">HONEYCOMB</a>
      </div>

      <!-- Collect the nav links, forms, and other content for toggling -->
      <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
          <!-- <li >
            <a href="#" class="title">Honeycomb</a>
          </li> -->
          <li>
            <a href="#" class="tag">Explore</a>
          </li>
          <li>
            <router-link :to="{name:'userInfo'}">About us</router-link>
          </li>
          <li>
            <a href="#">Community</a>
          </li>
          <!-- <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown
              <span class="caret"></span>
            </a>
            <ul class="dropdown-menu">
              <li>
                <a href="#">Action</a>
              </li>
              <li>
                <a href="#">Another action</a>
              </li>
              <li>
                <a href="#">Something else here</a>
              </li>
              <li role="separator" class="divider"></li>
              <li>
                <a href="#">Separated link</a>
              </li>
              <li role="separator" class="divider"></li>
              <li>
                <a href="#">One more separated link</a>
              </li>
            </ul>
          </li> -->
        </ul>
        <!-- <form class="navbar-form navbar-left">
          <div class="form-group">
            <input type="text" class="form-control" placeholder="Search">
          </div>
          <button type="submit" class="btn btn-default">Submit</button>
        </form> -->
        <ul class="nav navbar-nav navbar-right">
          <li>
            <router-link :to="{name:'Login'}">Sign in</router-link>
          </li>
          <li>
            <router-link :to="{name:'Register'}">Sign up</router-link>
          </li>
          <!-- <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown
              <span class="caret"></span>
            </a>
            <ul class="dropdown-menu">
              <li>
                <a href="#">Action</a>
              </li>
              <li>
                <a href="#">Another action</a>
              </li>
              <li>
                <a href="#">Something else here</a>
              </li>
              <li role="separator" class="divider"></li>
              <li>
                <a href="#">Separated link</a>
              </li>
            </ul>
          </li> -->
        </ul>
      </div>
      <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
  </nav>
</template>

<script>
export default {
    name: "nav-bar"
};
</script>
<style scoped>
.navbar-inverse {
    -webkit-border-radius: 0;
    -moz-border-radius: 0;
    border-radius: 0;
    background: linear-gradient(#24292e, #24292e);
    height: 70px;
}
.container-fluid {
    margin-top: 10px;
}
.navbar-inverse .navbar-header {
    height: 60px;
}

.navbar-inverse .navbar-nav > .open > a,
.navbar-inverse .navbar-nav > .open > a:focus,
.navbar-inverse .navbar-nav > .open > a:hover {
    color: #fff;
    background-color: #24292e;
}

.container-fluid > .navbar-collapse,
.container-fluid > .navbar-header,
.container > .navbar-collapse,
.container > .navbar-header {
    margin-right: -16px;
    margin-left: -16px;
    background-color: #24292e;
}

.navbar-inverse .navbar-brand {
    font-family: "Century Gothic", sans-serif;
    font-weight: 400;
    letter-spacing: 1px;
    font-size: 22px;
    color: #ffffff !important;
    background-color: transparent;
    margin-left: 15px;
}

@media (min-width: 768px) {
    .tag {
        margin-left: 20px;
    }
    .navbar-inverse .navbar-brand {
        font-family: "Century Gothic", sans-serif;
        font-weight: 400;
        letter-spacing: 1px;
        font-size: 22px;
        color: #ffffff !important;
        background-color: transparent;
        margin-left: 15px;
    }
}

@media (min-width: 900px) {
  .tag {
        margin-left: 25px;
    }
  .navbar-inverse .navbar-brand {
        font-family: "Century Gothic", sans-serif;
        font-weight: 400;
        letter-spacing: 1px;
        font-size: 22px;
        color: #ffffff !important;
        background-color: transparent;
        margin-left: 80px;
    }
}
.navbar-right {
    margin-right: 50px;
}
.navbar-inverse .navbar-nav>li>a {
    transition: all 0.6s ease;
    display: inline-block;
    color: #9d9d9d;
    text-transform: uppercase;
    font-family: 'Century Gothic', sans-serif;
    text-decoration: none;
    font-weight: normal;
    letter-spacing:0.8px
}
.navbar-inverse .navbar-nav>li>a:hover {
    color: #ffffff;
    font-weight: normal;
}
/* .navbar-brand {
    float: left;
    height: 50px;
    padding: 0px 15px;
    font-size: 18px;
    line-height: 20px;
    width: 120px;
}
.navbar-brand>img {
    display: block;
    max-width: 40%;
    height: auto;
} */
</style>
