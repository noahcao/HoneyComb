<template>
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
  </div>
</template>

<script>
export default {
  name: 'SelectBox',
  methods: {
    SelectPaper: function () {
      this.bus.$emit('SelectPaper', 'SelectPaper')
    },
    SelectAuthor: function () {
      this.bus.$emit('SelectAuthor', 'SelectAuthor')
    },
    SelectAll: function () {
      this.bus.$emit('SelectAll', 'SelectAll')
    }
  }

}
</script>
