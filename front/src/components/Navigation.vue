<template>
  <header class="overflow-hidden">
    <v-app-bar
        color="#009BDD"
        dark
        fixed
    >
      <v-app-bar-nav-icon @click="drawer = true"></v-app-bar-nav-icon>
      <v-toolbar-title>Polyrecrute</v-toolbar-title>
      <v-spacer></v-spacer>
      <router-link to="/Connexion">
        <v-btn v-if="!isConnected">Se connecter</v-btn>
      </router-link>
      <router-link to="/Profil">
        <v-btn v-if="isConnected" style="margin-right: 5px">{{currentUser.Name}}</v-btn>
      </router-link>
      <router-link to="/Connexion">
        <v-btn title="Se déconnecter" style="background: red" v-if="isConnected" @click="LogOut">
          <v-icon>exit_to_app</v-icon>
        </v-btn>
      </router-link>
    </v-app-bar>
    <v-navigation-drawer
        width="300"
        style="position:fixed; top:0; left:0;"
        v-model="drawer"
        absolute
        temporary>
      <h1 class="text-center" style="margin-top: 10px">Polyrecrute</h1>
      <v-list nav dense v-scroll:false>
        <v-list-item-group
            v-model="group"
            active-class="lighten-1--text text--accent-4">
          <v-list-item to="/">
            <v-list-item-icon>
              <v-icon>mdi-home</v-icon>
            </v-list-item-icon>
            <v-list-item-title style="white-space: normal">Accueil</v-list-item-title>
          </v-list-item>
          <v-list-item v-if="isConnected && currentUser.RoleId === 2 && !currentUser.IsAdmin" to="depotOffre">
            <v-list-item-icon>
              <v-icon>mdi-account</v-icon>
            </v-list-item-icon>
            <v-list-item-title style="white-space: normal">Dépôt d'offre d'apprentissage
            </v-list-item-title>
          </v-list-item>
          <v-list-item v-if="isConnected && currentUser.RoleId === 1 && !currentUser.IsAdmin" to="depotCandidature">
            <v-list-item-icon>
              <v-icon>mdi-account</v-icon>
            </v-list-item-icon>
            <v-list-item-title style="white-space: normal">Mon document descriptif
            </v-list-item-title>
          </v-list-item>
          <v-list-item v-if="isConnected && currentUser.RoleId === 2 && !currentUser.IsAdmin" to="saisieVoeux">
            <v-list-item-icon>
              <v-icon>mdi-clipboard-list</v-icon>
            </v-list-item-icon>
            <v-list-item-title style="white-space: normal">Saisie des voeux</v-list-item-title>
          </v-list-item>
          <v-list-item v-if="isConnected && currentUser.RoleId === 1 && !currentUser.IsAdmin" to="saisieVoeuxEtudiant">
            <v-list-item-icon>
              <v-icon>mdi-clipboard-list</v-icon>
            </v-list-item-icon>
            <v-list-item-title style="white-space: normal">Saisie des voeux</v-list-item-title>
          </v-list-item>
          <v-list-item v-if="isConnected && currentUser.IsAdmin" to="dashboard">
            <v-list-item-icon>
              <v-icon>mdi-desktop-mac-dashboard</v-icon>
            </v-list-item-icon>
            <v-list-item-title style="white-space: normal">Tableau de bord</v-list-item-title>
          </v-list-item>
        </v-list-item-group>
        <v-list-item v-if="!isConnected" to="Connexion">
          <v-list-item-icon>
            <v-icon>mdi-database-cog</v-icon>
          </v-list-item-icon>
          <v-list-item-title>Connexion</v-list-item-title>
        </v-list-item>
        <v-list-item v-if="isConnected && !currentUser.IsAdmin" to="/Profil">
          <v-list-item-icon>
            <v-icon>mdi-database-cog</v-icon>
          </v-list-item-icon>
          <v-list-item-title>Mon Profil</v-list-item-title>
        </v-list-item>
          <v-list-item v-if="isConnected && currentUser.IsAdmin" to="Connexion">
              <v-list-item-icon>
                  <v-icon>mdi-smart-card</v-icon>
              </v-list-item-icon>
              <v-list-item-title>Effectuer une inscription</v-list-item-title>
          </v-list-item>
        <v-list-item v-if="isConnected && !currentUser.IsAdmin" to="survey">
          <v-list-item-icon>
            <v-icon>mdi-forum</v-icon>
          </v-list-item-icon>
          <v-list-item-title style="white-space: normal">Questionnaire
          </v-list-item-title>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
  </header>
</template>

<script>
import {mapState} from 'vuex';
import StudentDataService from "@/service/StudentDataService";

export default {
  name: '/',
  components: {
    // Slide
  },
  data: () => ({
    drawer: false,
    group: null
  }),
  created(){
      if(window.sessionStorage.getItem("UserToken") == '' || window.sessionStorage.getItem("UserToken") == null || window.sessionStorage.getItem("UserToken") === undefined ){
        this.$store.commit('CONNEXION_MANAGEMENT', false);
      }else{
        this.$store.commit('CONNEXION_MANAGEMENT', true);
        StudentDataService.getConnectedUserDetails().then(response => {
          switch (response.status) {
            case 200 :
              this.$store.commit('SET_CURRENTUSER_FROM_JSON', response.data);
              break;
          }
        }).catch(err => {
          console.log("erreur : " + err);
        });
      }
  },
  methods: {
    LogOut(){
      window.sessionStorage.clear();
      this.$store.commit('CONNEXION_MANAGEMENT', false);
    }
  },
  computed: {
    ...mapState({
      isConnected: 'isConnected',
      currentUser: 'currentUser'
    }),
  }
}
</script>

<style>
a {
  text-decoration: none;
}
</style>
