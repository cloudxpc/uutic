// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue';
import App from './App';
import router from './router';

// import 'jquery/jquery.min';
// import 'bootstrap/dist/js/bootstrap.bundle.min';
// import 'bootstrap/dist/css/bootstrap.min.css';

import VueMaterial from 'vue-material';
import 'vue-material/dist/vue-material.min.css';
// import 'vue-material/dist/theme/default.css';
import './mytheme.scss';

import {eventBus} from './services/eventbus';
import AxiosConfig from './axios.config';

Vue.use(VueMaterial);
Vue.use(AxiosConfig);

Vue.config.productionTip = false;

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
});
