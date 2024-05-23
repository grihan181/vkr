<template>
    <v-toolbar class="text-blue container-pa">
      <v-toolbar-title class="font-weight-bold">
        ТОЧНОвСРОК
      </v-toolbar-title>
      <v-tabs
        v-model="tab"
        align-tabs="end"
        color="#477BFF"
      >
        <v-tab :value="1">Продукты</v-tab>
        <v-tab :value="2">Заказы</v-tab>
      </v-tabs>
    </v-toolbar>


    <v-tabs-window v-model="tab">
      <ProductsPage></ProductsPage>
      <OrderPage></OrderPage>
    </v-tabs-window>
</template>

<script setup>
import {ref} from "vue";
import ProductsPage from "@/components/ProductsPage.vue";
import OrderPage from "@/components/OrderPage.vue";

const tab = ref(null)
</script>

<style>
.container-pa {
  padding: 0 240px;
}
.title {
  font-size: 32px;
}
</style>
