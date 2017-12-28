<template>
  <div style="margin-top: 40px;">
    <div class="md-layout md-alignment-top-center">
      <div class="md-layout-item md-size-75">
        <data-table></data-table>
      </div>
    </div>
    <div>
      <md-button class="md-primary md-raised">OK</md-button>
      <md-button class="md-accent md-raised">OK</md-button>
    </div>

  </div>
</template>

<script>
  import DataTable from './DataTable';

  export default {
    components: {DataTable},
    name: 'Welcome'
  };
</script>
