document.querySelector('tbody').innerHTML = `<tr>${'<td></td>'.repeat(3)}</tr>`.repeat(10);

document.getElementById('table-refresh').addEventListener('submit', event => {
	event.preventDefault();
	const rows = document.querySelectorAll('tbody tr');
	let sum = 0;
	fetch('/data')
		.then(res => res.json())
		.then(entries => entries.forEach(({id, name, value}, i) => {
			rows[i].innerHTML = `<td>${id}</td><td>${name}</td><td>${value}</td>`;
			sum += value;
		}))
		.finally(_ => {
			document.getElementById('total').innerText = sum;
		})
		.catch(error => {
			console.log(error);
		});
});