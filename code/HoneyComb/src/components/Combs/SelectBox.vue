<template>
  <div>
    <div class='SelectType'>
      <ul>
        <li>
          <a href='#' v-on:click='SelectPaper'>
            <i class='fa fa-circle'></i>
            Papers
          </a>
        </li>

        <li>
          <a href='#' v-on:click='SelectAll'>
            <i class='fa fa-circle'></i>
            All
          </a>
        </li>
      </ul>
    </div>

    <div class='SelectLevel'>
      <ul>
        <li>
          <a href='#' v-on:click='SelectTwoLevel'>
            <i class='fa fa-circle'></i>
            2-levels
          </a>
        </li>
        <li>
          <a href='#' v-on:click='SelectThreeLevel'>
            <i class='fa fa-circle'></i>
            3-levels
          </a>
        </li>
        <li>
          <a href='#' v-on:click='SelectFourLevel'>
            <i class='fa fa-circle'></i>
            4-levels
          </a>
        </li>
      </ul>
    </div>

    <div class='tips'>
      <p>
        <b>Node in Net:</b>
      </p>
      <ul>
        <li>
          <p class='authorNode'>
            <i class='fa fa-circle'></i>
            author
          </p>

        </li>
        <li>
          <p class='time1'>
            <i class='fa fa-circle'></i>
            paper(xxxx-xxxx)
          </p>
        </li>
        <li>
          <p class='time2'>
            <i class='fa fa-circle'></i>
            paper(xxxx-xxxx)
          </p>
        </li>
        <li>
          <p class='time3'>
            <i class='fa fa-circle'></i>
            paper(xxxx-xxxx)
          </p>
        </li>
        <li>
          <p class='time4'>
            <i class='fa fa-circle'></i>
            paper(xxxx-xxxx)
          </p>
        </li>
      </ul>
    </div>

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

<style scoped>
p {
  color: white;
}
.SelectType {
  margin-top: 50px;
  margin-bottom: 30px;
}

.SelectType a {
  color: white;
  font-family: "Century Gothic", sans-serif;
  font-size: 16px;
  margin-bottom: 5px;
}

.SelectLevel {
  margin-top: 30px;
  margin-bottom: 30px;
}

.SelectLevel a {
  color: white;
  font-family: "Century Gothic", sans-serif;
  font-size: 16px;
  margin-bottom: 5px;
}

.tips {
  margin-top: 100px;
}

.authorNode i {
  color: #2c3e50;
}

.time1 i {
  color: springgreen;
}

.time2 i {
  color: red;
}

.time3 i {
  color: coral;
}

.time4 i {
  color: blue;
}
</style>