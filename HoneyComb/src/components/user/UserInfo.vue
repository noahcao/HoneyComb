<template>
  <div class="row">
    <div class="col-sm-0 col-md-1"></div>
    <div class="col-xs-12 col-sm-4 col-md-3">
      <div class="thumbnail">
        <img class="img-rounded avatar" src="../../assets/pic/dft-header.png" alt="70x70">
        <div class="caption">
          <h3>Lena Oxton</h3>
          <p>...</p>
          <p>
            <a href="#" class="btn btn-primary" role="button">Button</a>
            <a href="#" class="btn btn-default" role="button">Button</a>
          </p>
        </div>
      </div>
    </div>
    <div class="col-sm-8 col-md-7">

    </div>
    <div class="col-sm-0 col-md-1"></div>
  </div>
</template>

<script>
export default {
  name: 'userInfo'
}
</script>

<style scoped>
.thumbnail {
  display: block;
  padding: 4px;
  margin-bottom: 20px;
  line-height: 1.42857143;
  background-color: rgba(255, 255, 255, 0);
  border: none;
  border-right: 1px solid rgba(36, 41, 46, 0.6);
  border-radius: 0px;
  border-right-style: groove;
  -webkit-transition: border 0.2s ease-in-out;
  -o-transition: border 0.2s ease-in-out;
  transition: border 0.2s ease-in-out;
  box-shadow: none;
}
.thumbnail > img {
  display: block;
  /* max-width: 100%; */
  height: 240px;
  margin-top: 60px;
  margin-left: 0px;
}
.avatar {
}
</style>
