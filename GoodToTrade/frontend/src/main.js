import { createApp } from 'vue';
import App from './App.vue';
import router from './router'; // import the router configuration

const app = createApp(App);

app.use(router); // Use the router

app.mount('#app');
