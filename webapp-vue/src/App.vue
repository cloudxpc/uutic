<template>
  <div id="app">
    <app-header></app-header>
    <app-main></app-main>
    <app-footer></app-footer>
  </div>
</template>

<script>
  import AppHeader from './components/AppHeader';
  import AppMain from './components/AppMain';
  import AppFooter from './components/AppFooter';

  export default {
    name: 'app',
    components: {
      'app-header': AppHeader,
      'app-main': AppMain,
      'app-footer': AppFooter
    }
  };
</script>

<style>
  #app {
    font-family: "Microsoft Yahei", Roboto, "Helvetica Neue", Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    min-height: 100vh;
    padding-top: 48px;
    padding-bottom: 60px;
  }
</style>
