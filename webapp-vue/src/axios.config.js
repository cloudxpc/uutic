
import axios from 'axios';
import {eventBus} from './services/eventbus';

export default {
  install: (Vue, options) => {
    Vue.prototype.$axios = axios;

    axios.defaults.baseURL = 'http://localhost:8080/api';
    axios.defaults.headers.common['Authorization'] = 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ1dXRpYyIsImlhdCI6MTUxNDUyNTA4Mn0.4D9_rMTA1lRFuiXbQyJ1OWy3OQaHtN7eaFAdhDzE64I';

    // Add a request interceptor
    axios.interceptors.request.use(config => {
      eventBus.$emit('loading-start');
      return config;
    }, error => {
      eventBus.$emit('loading-end');
      console.log('Global Request Error Handler: ', error);
      return Promise.reject(error);
    });

    // Add a response interceptor
    axios.interceptors.response.use(response => {
      eventBus.$emit('loading-end');
      return response;
    }, error => {
      eventBus.$emit('loading-end');
      console.log('Global Response Error Handler: ', error);
      return Promise.reject(error);
    });
  }
};
