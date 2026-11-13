import { createRouter, createWebHashHistory } from 'vue-router';
import { useAuth } from '../stores/auth';
import SignIn from '../components/SignIn.vue';
import Settings from '../components/Settings.vue';
import MyDashboard from '../components/MyDashboard.vue';

// Dynamic imports for module components
import EnvironmentalSensors from '../components/EnvironmentalSensors.vue';
import EnvironmentalSensorDetail from '../components/EnvironmentalSensorDetail.vue';
import SensorReadings from '../components/SensorReadings.vue';
import SensorReadingDetail from '../components/SensorReadingDetail.vue';
import SensorThresholds from '../components/SensorThresholds.vue';
import SensorThresholdDetail from '../components/SensorThresholdDetail.vue';
import SensorAlerts from '../components/SensorAlerts.vue';
import SensorAlertDetail from '../components/SensorAlertDetail.vue';

const routes = [
  {
    path: '/',
    name: 'home',
    redirect: '/dashboard'
  },
  {
    path: '/dashboard',
    name: 'Dashboard',
    component: MyDashboard
  },
  
  // Dynamic module routes
  {
    path: '/environmentalSensors',
    name: 'EnvironmentalSensors',
    component: EnvironmentalSensors
  },
  {
    path: '/environmentalSensor/:id',
    name: 'EnvironmentalSensorDetail',
    component: EnvironmentalSensorDetail,
    props: true
  },
  {
    path: '/sensorReadings',
    name: 'SensorReadings',
    component: SensorReadings
  },
  {
    path: '/sensorReading/:id',
    name: 'SensorReadingDetail',
    component: SensorReadingDetail,
    props: true
  },
  {
    path: '/sensorThresholds',
    name: 'SensorThresholds',
    component: SensorThresholds
  },
  {
    path: '/sensorThreshold/:id',
    name: 'SensorThresholdDetail',
    component: SensorThresholdDetail,
    props: true
  },
  {
    path: '/sensorAlerts',
    name: 'SensorAlerts',
    component: SensorAlerts
  },
  {
    path: '/sensorAlert/:id',
    name: 'SensorAlertDetail',
    component: SensorAlertDetail,
    props: true
  },

  // Core application routes
  {
    path: '/signin',
    name: 'SignIn',
    component: SignIn
  },
//  {
//    path: '/users',
//    name: 'Users',
//    component: Users,
//    meta: { requiresAuth: true }
//  },
  {
    path: '/settings',
    name: 'Settings',
    component: Settings,
    meta: { requiresAuth: true }
  },
  {
    path: '/:pathMatch(.*)*',
    redirect: '/dashboard'
  }
];

const router = createRouter({
  history: createWebHashHistory(import.meta.env.BASE_URL),
  routes,
  scrollBehavior(to, from, savedPosition) {
    return savedPosition || { top: 0 };
  }
});

// Navigation guard for authentication
router.beforeEach((to, from, next) => {
  const auth = useAuth();
  
  if (to.meta.requiresAuth && !auth.isAuthenticated) {
    next({ name: 'SignIn', query: { redirect: to.fullPath } });
  } else if (to.name === 'SignIn' && auth.isAuthenticated) {
    next({ name: 'Dashboard' });
  } else {
    next();
  }
});

export default router;
