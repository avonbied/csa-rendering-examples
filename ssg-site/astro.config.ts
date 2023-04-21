import { defineConfig } from 'astro/config';

// https://astro.build/config
export default defineConfig({
	port: (process.env.PORT ?? 3000),
	server: {
		host: true,
	}
});
