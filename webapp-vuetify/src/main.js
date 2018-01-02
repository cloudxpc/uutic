// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import Vuetify from 'vuetify'

import {eventBus} from './services/eventbus';
import AxiosConfig from './axios.config';

import 'vuetify/dist/vuetify.min.css';
import 'material-design-icons/iconfont/material-icons.css';

Vue.use(Vuetify, {
  theme: {
    primary: '#3F51B5',
    secondary: '#b0bec5',
    accent: '#8c9eff',
    error: '#b71c1c'
  }
});
Vue.use(AxiosConfig);

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App },
  data: {
    loading_count: 0
  },
  computed: {
    isLoading () {
      return this.loading_count > 0;
    }
  },
  created () {
    eventBus.$on('loading-start', () => {
      this.loading_count++;
    });
    eventBus.$on('loading-end', () => {
      this.loading_count--;
    });
  },
  destroyed () {
    eventBus.$off('loading-start');
    eventBus.$off('loading-end');
  }
})
