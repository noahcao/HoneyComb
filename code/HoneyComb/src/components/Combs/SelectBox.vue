<template>
  <div>
    <ul>
      <li>
        <a href='#' v-on:click='SelectPaper'>
          <i class='fa fa-circle'></i>
          <b>Papers</b>
        </a>
      </li>
      <!-- <li>
        <a href='#' v-on:click='SelectAuthor'>
          <i class='fa fa-circle'></i>
          <b>Authors</b>
        </a>
      </li> -->
      <li>
        <a href='#' v-on:click='SelectAll'>
          <i class='fa fa-circle'></i>
          <b>All</b>
        </a>
      </li>
    </ul>

    <ul>
      <li>
        <a href='#' v-on:click='SelectTwoLevel'>
          <i class='fa fa-circle'></i>
          <b>2-levels</b>
        </a>
      </li>
      <li>
        <a href='#' v-on:click='SelectThreeLevel'>
          <i class='fa fa-circle'></i>
          <b>3-levels</b>
        </a>
      </li>
      <li>
        <a href='#' v-on:click='SelectFourLevel'>
          <i class='fa fa-circle'></i>
          <b>4-levels</b>
        </a>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'SelectBox',
  methods: {
    SelectPaper: function () {
      this.bus.$emit('SelectPaper', 'SelectPaper')
    },
    SelectAll: function () {
      this.bus.$emit('SelectAll', 'SelectAll')
    },
    SelectTwoLevel: function () {
      this.bus.$emit('SelectTwoLevel', 'SelectTwoLevel')
    },
    SelectThreeLevel: function () {
      this.bus.$emit('SelectThreeLevel', 'SelectThreeLevel')
    },
    SelectFourLevel: function () {
      this.bus.$emit('SelectFourLevel', 'SelectFourLevel')
    }
  }
}
</script>
