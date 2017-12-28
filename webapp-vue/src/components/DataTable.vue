<template>
  <div>
    <md-table v-model="filteredUsers" :md-sort.sync="currentSort" :md-sort-order.sync="currentSortOrder"
              :md-sort-fn="customSort" md-card md-fixed-header>
      <md-table-toolbar>
        <div class="md-toolbar-section-start">
          <h1 class="md-title">数据表格</h1>
          <md-button class="md-icon-button" @click="refreshData">
            <md-icon>refresh</md-icon>
          </md-button>
        </div>

        <md-field md-clearable class="md-toolbar-section-end">
          <md-input placeholder="Search by name..." v-model="searchText" @input="searchUser"/>
        </md-field>
      </md-table-toolbar>

      <md-table-empty-state
        md-label="No users found"
        :md-description="`No user found for this '${searchText}' query. Try a different search term or create a new user.`">
        <md-button class="md-primary md-raised">Create New User</md-button>
      </md-table-empty-state>

      <md-table-row slot="md-table-row" slot-scope="{ item }">
        <md-table-cell md-label="ID" md-sort-by="id" md-numeric>{{ item.id }}</md-table-cell>
        <md-table-cell md-label="Name" md-sort-by="name">{{ item.name }}</md-table-cell>
        <md-table-cell md-label="Email" md-sort-by="email">{{ item.email }}</md-table-cell>
        <md-table-cell md-label="Gender" md-sort-by="gender">{{ item.gender }}</md-table-cell>
        <md-table-cell md-label="Job Title" md-sort-by="title">{{ item.title }}</md-table-cell>
      </md-table-row>
    </md-table>
  </div>
</template>

<script>
  import _ from 'lodash';
  export default {
    name: 'DataTable',
    data () {
      return {
        currentSort: 'id',
        currentSortOrder: 'asc',
        searchText: '',
        users: [],
        filteredUsers: []
      };
    },
    methods: {
      customSort (value) {
        return value.sort((a, b) => {
          const sortBy = this.currentSort;
          if (sortBy === 'id') {
            return this.currentSortOrder === 'asc' ? a[sortBy] - b[sortBy] : b[sortBy] - a[sortBy];
          } else {
            return this.currentSortOrder === 'asc' ? a[sortBy].localeCompare(b[sortBy]) : b[sortBy].localeCompare(a[sortBy]);
          }
        });
      },
      refreshData: _.debounce(function () {
        this.$axios.get('/hello').then(response => {
          this.users = response.data;
          this.searchUser();
        });
      }, 300),
      searchUser () {
        this.filteredUsers = this.searchText ? this.users.filter(item => {
          return item.name.toLowerCase().includes(this.searchText.toString().toLowerCase());
        }) : this.users;
      }
    }
  };
</script>
