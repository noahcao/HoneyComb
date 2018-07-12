<template>
    <div class="mobile">
		<!-- Checkbox to toggle the menu -->
		<input type="checkbox" id="tm" />
		<!-- The menu -->
		<ul class="sidenav">
		    <li><a href="#" v-on:click="SelectPaper"><i class="fa fa-circle"></i><b>Papers</b></a></li>
			<li><a href="#" v-on:click="SelectAuthor"><i class="fa fa-circle"></i><b>Authors</b></a></li>
			<li><a href="#" v-on:click="SelectAll"><i class="fa fa-circle"></i><b>All</b></a></li>
		</ul>
		<!-- Content area -->
		<section id='net'>
			<!-- Label for #tm checkbox -->
			<label for="tm">Select Here</label>
		</section>
	</div>
</template>

<script>
import * as d3 from "d3";
export default {
  name: "SelectBox",
  methods: {
    SelectPaper: function() {
      var sth = ["1", "2"];
      console.log("clicked!");
      this.bus.$emit("SelectPaper", sth);
    },
    SelectAuthor: function() {},
    SelectAll: function() {}
  },
  
};
</script>

<style>
* {
  margin: 0;
  padding: 0;
}
#wrapper {
  width: 645px;
  height: 500px;
  margin: 35px auto 50px auto;
}

h2 {
  color: #fff;
  font: 300 40px "Open Sans";
  width: 300px;
  padding: 30px;
  float: left;
}

.mobile {
  float: left;
  position: absolute;
  box-shadow: 0 0 25px 0 rgba(27, 27, 27, 0.5);
  overflow: hidden;
}
/*Hiding the checkbox*/
#tm {
  display: none;
}
/*Content area*/
.mobile section {
  background: #595959;
  opacity: 1;
  width: 320px;
  height: 600px;
  position: relative;
  transition: all 0.25s;
}
.mobile section label {
  color: white;
  font: bold 14px Montserrat;
  text-align: center;
  border: 2px solid white;
  border-radius: 4px;
  display: block;
  padding: 10px 0;
  width: 60%;
  position: absolute;
  left: 20%;
  top: 150px;
  cursor: pointer;
  text-transform: uppercase;
}
/*Nav styles*/
.sidenav {
  background: rgb(50, 60, 60);
  width: 200px;
  position: absolute;
  right: 0;
  top: 0;
  bottom: 0;
  padding-top: 100px;
  margin-bottom: 0px;
}
.sidenav li {
  list-style-type: none;
}
.sidenav a {
  color: white;
  text-decoration: none;
}
.sidenav b {
  font: bold 12px/48px Montserrat;
  display: block;
  opacity: 0;
  transform: translateX(-50px);
  transition: all 0.4s;
}
.sidenav i {
  display: block;
  width: 50px;
  float: left;
  font-size: 16px;
  line-height: 48px;
  text-align: center;
}
/*Animation controls using checkbox hack*/
/*Animate content area to the right*/
#tm:checked ~ section {
  transform: translateX(-200px);
}
/*Animate links from right to left + fade in effect*/
#tm:checked ~ .sidenav b {
  opacity: 1;
  transform: translateX(0);
}

/*Adding delay to link animation - in multiples of .08s*/
/*One can use jQuery also for creating the delayed effect. But I will stick to manual CSS for this walkthrough.*/
#tm:checked ~ .sidenav li:nth-child(1) b {
  transition-delay: 0.08s;
}
#tm:checked ~ .sidenav li:nth-child(2) b {
  transition-delay: 0.16s;
}
#tm:checked ~ .sidenav li:nth-child(3) b {
  transition-delay: 0.24s;
}
#tm:checked ~ .sidenav li:nth-child(4) b {
  transition-delay: 0.32s;
}
#tm:checked ~ .sidenav li:nth-child(5) b {
  transition-delay: 0.4s;
}
#tm:checked ~ .sidenav li:nth-child(6) b {
  transition-delay: 0.48s;
}
</style>


