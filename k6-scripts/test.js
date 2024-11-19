import http from 'k6/http';
import { sleep } from 'k6';
import { check } from 'k6';

export const options = {
    scenarios: {
        constant_load: {
            executor: 'constant-vus',
            vus: 10,
            duration: '30s',
        },
        ramping_load: {
            executor: 'ramping-vus',
            startVUs: 1,
            stages: [
                { duration: '10s', target: 10 },
                { duration: '20s', target: 50 },
                { duration: '10s', target: 0 },
            ],
            gracefulRampDown: '5s',
        },
        constant_rate: {
            executor: 'constant-arrival-rate',
            rate: 20,
            timeUnit: '1s',
            duration: '30s',
            preAllocatedVUs: 50,
            maxVUs: 100,
        },
    },
};

export default function () {
    const res = http.get('http://nginx/products');
    check(res, {
        'status is 200': (r) => r.status === 200,
        'body contains products': (r) => r.body.includes('testName'),
    });

    // Think time
    sleep(Math.random() * 2 + 1); // Випадковий час очікування від 1 до 3 секунд
}
