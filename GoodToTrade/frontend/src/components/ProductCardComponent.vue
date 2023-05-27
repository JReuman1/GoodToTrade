<template>
    <div>
      <tinder
        v-if="products.length > 0"
        ref="tinder"
        @like="like"
        @dislike="dislike"
      >
        <tinder-card class="product-card" v-for="product in products" :key="product.id">
          <div
            :style="{ backgroundImage: `url(${product.imageUrl})` }"
          >
            <div class="product-info">
              <h2>{{ product.productName }}</h2>
            </div>
            <div class="actions">
                <button class="btn dislike" @click="dislike"></button>
                <button class="btn like" @click="like(product.id)"></button>
            </div>
          </div>
        </tinder-card>
      </tinder>
    </div>
  </template>
  
  <script>
  import axios from "axios";
  import { Tinder, TinderCard } from "vue-tinder";
  
  export default {
    components: {
      Tinder,
      TinderCard,
    },
    data() {
      return {
        products: [],
      };
    },
    async created() {
      try {
        const response = await axios.get("http://localhost:8080/api/products");
        this.products = response.data;
      } catch (error) {
        console.error(error);
      }
    },
    methods: {
      dislike() {
        this.products.splice(this.$refs.tinder.index, 1);
      },
      async like(productId) {
        try {
          const response = await axios.post(
            "http://localhost:8080/api/likes",
            {
              userId: "1", // Id del usuario actual (debes proporcionar el valor correcto)
              productId: productId,
            },
            {
              headers: {
                Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
              },
            }
          );
          console.log(response.data);
          this.products.splice(this.$refs.tinder.index, 1); // Eliminar la foto de la lista
        } catch (error) {
          console.error(error);
        }
      },
    },
  };
  </script>
  
  <style scoped>
  .product-card {
    /* estilos de la tarjeta del producto aquí */
  }

  .btn {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  background-repeat: no-repeat;
  background-position: center;
  background-size: contain;
  border: none;
}

.dislike {
  background-image: url('https://uploads.codesandbox.io/uploads/user/992079af-4d21-44ac-8853-43908c0d9b78/B5S4-nope.png');
}

.like {
  background-image: url('https://uploads.codesandbox.io/uploads/user/992079af-4d21-44ac-8853-43908c0d9b78/B3NV-like.png');
}

.actions {
  display: flex;
  justify-content: center;
  gap: 20px;
}

.product-card {
  width: 100%;
  height: 100%;
  background-position: center;
  background-repeat: no-repeat;
  background-size: 20% 20%;
}

  </style>
  