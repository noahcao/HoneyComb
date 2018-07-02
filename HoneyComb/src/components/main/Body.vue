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
        <div class="col-lg-6">
            <div class="input-group">
                <input type="text" class="form-control" placeholder="Search for...">
                <span class="input-group-btn">
                    <button class="btn btn-default" type="button">Go!</button>
                </span>
            </div>
            <!-- /input-group -->
        </div>
        <!-- /.col-lg-6 -->
    </section>
</template>
<script>
export default {
    name: "main-body"
};
</script>
<style scoped>
.form-control {
    
    border: 1px solid grey;
    border-radius: 4px;
}

input{
    transition: all 1s ease;
    background-color: rgba(36,41,46, 0.3);
    outline:none;
    box-shadow: none;
}
input:hover, input:focus{
    background-color: rgba(36,41,46, 0.5);
    border:1px solid grey;
    box-shadow: none;
}

</style>
