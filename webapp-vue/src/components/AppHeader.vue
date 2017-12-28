<template>
  <header>
    <md-progress-bar class="md-accent" md-mode="indeterminate" v-if="$root.isLoading"></md-progress-bar>
    <md-toolbar class="md-primary md-dense">
      <div class="md-toolbar-section-start">
        <span class="md-title">这是标题</span>
        <md-tabs class="md-primary" md-sync-route>
          <md-tab id="tab-home" md-label="首页" to="/"></md-tab>
          <md-tab id="tab-pages" md-label="菜单2" to="/hello"></md-tab>
          <md-tab id="tab-posts" md-label="菜单3" to="/components/tabs/posts"></md-tab>
          <md-tab id="tab-settings" md-label="菜单4" to="/components/tabs/settings"></md-tab>
          <md-tab id="tab-disabled" md-label="禁用菜单" md-disabled></md-tab>
        </md-tabs>
      </div>
      <div class="md-toolbar-section-end">
        <md-button @click="buttonClick">Refresh</md-button>
        <md-menu md-align-trigger>
          <md-button md-menu-trigger class="md-icon-button">
            <md-icon>menu</md-icon>
          </md-button>
          <md-menu-content>
            <md-menu-item>
              <md-button>Item1</md-button>
            </md-menu-item>
            <md-menu-item><md-button>Item2</md-button></md-menu-item>
            <md-menu-item>Item3</md-menu-item>
          </md-menu-content>
        </md-menu>
      </div>
    </md-toolbar>
  </header>
</template>

<script>
  export default {
    name: 'AppHeader',
    data () {
      return {
        loading: false
      };
    },
    methods: {
      buttonClick () {
        this.$axios.get('/hello').then(function (response) {
          console.log(response);
        });
      }
    }
  };
</script>

<style scoped>
  .md-toolbar {
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    z-index: 99;
  }

  .md-progress-bar {
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    height: 3px;
    z-index: 999;
  }

  .md-tabs {
    height: 100%;
  }
</style>
