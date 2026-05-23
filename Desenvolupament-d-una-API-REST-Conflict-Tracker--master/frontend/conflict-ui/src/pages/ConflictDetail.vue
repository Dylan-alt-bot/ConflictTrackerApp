<template>

<div v-if="loading">
    Carregant...
</div>



<div v-else-if="conflict">

    <button class="back-btn" @click="goBack">
        ← Tornar
    </button>

    <h1>{{ conflict.name }}</h1>

    <p>
        <b>Estat:</b>
        <span :class="statusClass(conflict.status)">
            {{ conflict.status }}
        </span>
    </p>

    <p>
        <b>Data inici:</b>
        {{ formatDate(conflict.startDate) }}
    </p>

    <p>
        {{ conflict.description }}
    </p>

    <hr>

    <h2>Països implicats</h2>



    <ul>

        <li v-for="country in conflict.countries"

            :key="country.id"

            class="country-item">

            

            <img
                class="flag"
                :src="flagUrl(country.code)"
                :alt="country.name"
            />

            <span>

                {{ country.name }}

                ({{ country.code }})

            </span>

        </li>

    </ul>



    <h2>Faccions implicades</h2>

<div v-if="factions.length">

    <ul>

        <li v-for="f in factions" :key="f.id">

            <b>{{ f.name }}</b>

            <br>

            <div class="flags-row">

                <div
                    v-for="countryName in f.supportingCountryNames"
                    :key="countryName"
                    class="country-item"
                >

                    <img
                        class="flag"
                        :src="flagUrlFromName(countryName)"
                        :alt="countryName"
                    />

                    <span>{{ countryName }}</span>

                </div>

            </div>

        </li>

    </ul>

</div>

<div v-else>
    No hi ha faccions
</div>

    <h2>Esdeveniments</h2>

    <div v-if="events.length">

        <ul>

            <li v-for="e in events" :key="e.id">

                <b>

                    {{ formatDate(e.eventDate) }}

                </b>

                —

                {{ e.location }}



                <br>



                {{ e.description }}

            </li>

        </ul>

    </div>


    <div v-else>

        No hi ha events

    </div>



</div>

</template>



<script setup>

import { useRouter } from "vue-router";

import { ref, onMounted } from "vue";

import { useRoute } from "vue-router";

import { api } from "../services/api";


const router = useRouter();

const route = useRoute();

const goBack = () => {
    router.back();
};


const conflict = ref(null);

const factions = ref([]);

const events = ref([]);

const loading = ref(true);



// 🌍 FLAG API

const flagUrl = (code) => {

    if (!code) return "";

    return `https://flagcdn.com/w40/${code.toLowerCase()}.png`;

};



onMounted(async () => {

    const id = route.params.id;



    const [conflictRes, factionRes, eventRes] = await Promise.all([

        api.get(`/conflicts/${id}`),

        api.get(`/factions/conflict/${id}`),

        api.get(`/events/conflict/${id}`)

    ]);



    conflict.value = conflictRes.data;

    factions.value = factionRes.data;

    events.value = eventRes.data;



    loading.value = false;

});



const formatDate = (date) => {

    return new Date(date).toLocaleDateString("ca-ES");

};



const statusClass = (status) => {

    return {

        ACTIVE: "green",

        FROZEN: "orange",

        ENDED: "red"

    }[status] || "";

};

const nameToCodeMap = {
    Ukraine: "ua",
    Russia: "ru",
    "United States Of America": "us",
    Germany: "de",
    "United Kingdom": "gb",
    France: "fr",
    Italy: "it",
    Poland: "pl",
    Japan: "jp",
    China: "cn"
};

const flagUrlFromName = (name) => {
    if (!name) return "";

    const code = nameToCodeMap[name] || "";

    return code ? `https://flagcdn.com/w40/${code}.png` : "";
};

</script>



<style scoped>

.green { color: green; font-weight: bold; }

.orange { color: orange; font-weight: bold; }

.red { color: red; font-weight: bold; }



h2 {

    margin-top: 30px;

}



ul {

    padding-left: 20px;

}



li {

    margin-bottom: 15px;

}



/* 🌍 FLAGS */

.country-item {

    display: flex;

    align-items: center;

    gap: 10px;

}



.flag {

    width: 24px;

    height: 16px;

    border-radius: 3px;

    box-shadow: 0 0 2px rgba(0,0,0,0.2);

}

.flags-row {
    display: flex;
    flex-wrap: wrap;
    gap: 10px;
    margin-top: 8px;
}

.country-item {
    display: flex;
    align-items: center;
    gap: 6px;
}

.flag {
    width: 22px;
    height: 15px;
    border-radius: 3px;
    box-shadow: 0 0 2px rgba(0,0,0,0.2);
}

.back-btn {
    background: #1f2937;
    color: white;
    border: none;
    padding: 8px 12px;
    border-radius: 8px;
    cursor: pointer;
    margin-bottom: 15px;
}

.back-btn:hover {
    background: #111827;
}

</style>