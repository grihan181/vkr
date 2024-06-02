<script setup>
import {computed, ref} from 'vue'
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

const listData = ref([]);
const allOrders= ref(null);
const lateOrders= ref(null);


fetch('http://localhost:8080/order/0/20', {
  method: 'GET',
})
  .then((resp) => resp.json())
  .then(({content}) => listData.value = content)
  .then(() => {
    console.log(listData.value)
  })

fetch('http://localhost:8080/order/getCountAllOrders', {
  method: 'GET',
})
  .then((resp) => resp.json())
  .then((content) => allOrders.value = content)

fetch('http://localhost:8080/order/getCountAllLateOrders', {
  method: 'GET',
})
  .then((resp) => resp.json())
  .then((content) => lateOrders.value = content)


const amount= ref([])
const arrivalDate = ref([])
const colors = ref([])
const startDate = ref(new Date())
const endDate = ref(new Date())


const chartAmount = computed(() => amount.value)
const chartArrivalDate = computed(() => arrivalDate.value)


const chartData = computed(() => {
  return {
    labels: [...chartArrivalDate.value],
      datasets: [
    {
      label: 'Колличество товара по дням',
      borderWidth: 1,
      backgroundColor: colors.value,
      data: [...chartAmount.value],
    }
  ]
  }
})
const chartOptions = {
  responsive: true
}

const selectedOrder = ref(0)
const orderName = ref ('')

function updateChart(item) {
  selectedOrder.value = item.id
  orderName.value = item.product.name
  fetch(`http://localhost:8080/order/getDashboard/${item.product.id}`, {
    method: 'POST',
    headers: {'content-type': 'application/json;'},
    body: JSON.stringify({
      startDate: new Date(startDate.value).toISOString().split('T')[0],
      endDate: new Date(endDate.value).toISOString().split('T')[0]
    })
  })
    .then(res => res.json())
    .then(data => {
      amount.value = data.map((point) => point.amount)
      arrivalDate.value = data.map((point) => point.arrivalDate)
      colors.value = data.map((point) => {
        if (point.deliveryStatus === 'ON_TIME') return '#477BFF'
        else return 'red'
      })
    })
}


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
        <div class="d-flex flex-column ga-5">
          <v-card v-for="item in listData" v-bind:key="item.id" width="468px" height="144px" :variant="item.id === selectedOrder ?'outlined' : 'elevated'"  @click="updateChart(item)">
            <v-card-title>{{item.product.name}}</v-card-title>
            <v-card-text>
              <div class="w-100 d-flex justify-space-between">
                <span class="d-inline-block ">Поставщик</span>
                <span class="d-inline-block font-weight-bold">{{item.provider.name}}</span>
              </div>

              <div class="w-100 d-flex justify-space-between">
                <span class="d-inline-block ">Дата прибытия</span>
                <span v-if="item.deliveryStatus !== 'LATE'" class="d-inline-block font-weight-bold">{{item.arrivalDate}}</span>
                <div v-else class="d-flex flex-column align-end">
                  <span  class="d-inline-block font-weight-bold text-red">{{item.arrivalDate}}</span>
                  <span  class="d-inline-block font-weight-bold text-red">{{`Задерживается на ${item.lostInDays} дня-ей`}}</span>
                </div>
              </div>
              <div class="w-100 d-flex justify-space-between">
                <span class="d-inline-block ">Прибудет</span>
                <span class="d-inline-block font-weight-bold">{{item.amount + ' ед.'}}</span>
              </div>

            </v-card-text>
          </v-card>
        </div>
        <div style="width: 700px" class="d-flex flex-column ga-10">
          <div class="d-flex justify-space-between">
            <v-card rounded width="222px" height="144px">
              <v-card-title class="order-card-title">Всего заказов</v-card-title>
              <template #actions>
                <span class="orders-count">{{allOrders}}</span>
              </template>
            </v-card>
            <v-card class="orders-count-card" rounded width="222px" height="144px">
              <v-card-title class="order-card-title">Задерживаются</v-card-title>
              <template #actions>
                <span class="orders-count">{{lateOrders}}</span>
              </template>
            </v-card>
          </div>
          <v-card width="700px" height="550px" class="pa-10">
            <p class="font-weight-bold">График заказов: {{orderName}}</p>
            <p class="font-weight-bold">Деапозон дат</p>
            <div class="d-flex w-100 justify-space-between">
              <div style="width: 200px">
                <v-text-field label="от" variant="outlined"  type="date" v-model="startDate"></v-text-field>
              </div>
              <div style="width: 200px">
                <v-text-field label="до"  variant="outlined" type="date" v-model="endDate"></v-text-field>
              </div>
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
.order-card-title {
  font-size: 16px !important;
}
.orders-count {
  font-size: 40px;
  font-weight: 60;
}
.orders-count-card {
  border: 1px solid red;
}
</style>
