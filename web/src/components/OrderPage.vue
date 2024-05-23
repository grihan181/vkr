<script setup>
import {onMounted, ref} from 'vue'
import {Line} from 'vue-chartjs'
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend
} from 'chart.js'

ChartJS.register(
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend
)

const chartData = {
  labels: ['22.07', '23.07', '24.07', '25.07', '26.07', '27.07', '28.07'],
  datasets: [
    {
      label: 'Колличество товара по дням',
      borderColor: '#477BFF',
      backgroundColor: '#477BFF',
      data: [1000, 500, 2500, 1700, 2000, 700, 300]
    }
  ]
}
const chartOptions = {
  responsive: true
}


const page = ref(1)
const items = Array.from({length: 4}, (k, v) => ({
  title: 'Item ' + v + 1,
  text: 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Commodi, ratione debitis quis est labore voluptatibus! Eaque cupiditate minima, at placeat totam, magni doloremque veniam neque porro libero rerum unde voluptatem!',
}))


</script>

<template>
  <v-tabs-window-item :value="2">
    <div class="container-pa">
      <v-text-field
        variant="solo"
        width="468px"
        class="input-pa"
        append-inner-icon="mdi-magnify"
      ></v-text-field>
      <div class="d-flex justify-space-between">
        <v-data-iterator :items="items" :page="page">
          <template v-slot:default="{ items }">
            <template
              v-for="(item, i) in items"
              :key="i"
            >
              <v-card v-bind="item.raw" width="468px" height="144px"></v-card>

              <br>
            </template>
          </template>
        </v-data-iterator>
        <div style="width: 700px" class="d-flex flex-column ga-10">
          <div class="d-flex justify-space-between">
            <v-card width="222px" height="144px">

            </v-card>
            <v-card width="222px" height="144px">

            </v-card>
          </div>
          <v-card width="700px" height="550px" class="pa-10">
            <p class="font-weight-bold">График заказов: Двигатель</p>
            <p class="font-weight-bold">Деапозон дат</p>
            <div class="d-flex">
              <v-text-field label="от" width="50px" type="date"></v-text-field>
              <v-text-field label="до" width="50px" type="date"></v-text-field>
            </div>
            <div>
              <Line id="my-chart-id" :options="chartOptions" :data="chartData"/>
            </div>
          </v-card>
        </div>
      </div>

    </div>
  </v-tabs-window-item>
</template>

<style scoped>
.input-pa {
  padding: 12px 0;
}

.container-pa {
  padding: 12px 240px;
}
</style>
