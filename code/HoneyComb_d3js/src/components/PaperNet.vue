<template>
<div>
    <div class='col-md-2'>
        <select-box></select-box>
    </div>
    <div class='col-md-10'>
        <d3-force></d3-force>
    </div>
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
