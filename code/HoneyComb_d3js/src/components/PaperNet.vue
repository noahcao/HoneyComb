<template>
<div class='container-fluid'>
    <select-box></select-box>
    <d3-force></d3-force>

</div>
</template>

<script>
import D3Force from "./D3_Force.vue";
import SelectBox from "./SelectBox.vue";
export default {
  name: "PaperNet",
  components: {
    D3Force,
    SelectBox
  }
};
</script>

<style>
.container-fluid {
  padding-left: 0px;
  padding-right: 0px;
}

.col-md-2 {
  padding-left: 0px;
  padding-right: 0px;
}

.col-md-10 {
  padding-left: 0px;
  padding-right: 0px;
}
</style>

