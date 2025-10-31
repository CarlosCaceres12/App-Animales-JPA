const apiBase = "/gatos"

document.addEventListener('DOMContentLoaded', () => {
    document.getElementById("form-crear").addEventListener('submit', crearGato);
    document.getElementById("btn-cargar-gatos").addEventListener('click', cargarTodosLosGatos);
});

async function crearGato(event) {
    event.preventDefault();

    const nombre = document.getElementById('nombre').value;
    const npatas = document.getElementById('patas').value;
    const gato = {
        nombre,
        npatas: parseInt(npatas)
    };

    try {
        const url = apiBase + '/guardar'
        const response = await fetch(url, {
            method: 'POST',
            headers: { 'Content-type': 'application/json'},
            body: JSON.stringify(gato)
        });
        if (response.ok) {
            alert('Gato creado exitosamente');
            document.getElementById('form-crear').reset();
            cargarTodosLosGatos();
        } else {
            alert('Error creando gato');
        }
    } catch (error) {
        console.error('Error al crear un gato: ', error);
    }

}

async function cargarTodosLosGatos() {
    try {
        const response = await fetch(apiBase);
        const gatos = await response.json();
        mostrarGatos(gatos);
    } catch (error) {
        console.error("Error cargando gatos: ", error);
    }
}

function mostrarGatos(gatos) {
    const listGatos = document.getElementById('gatos-list');

    listGatos.innerHTML = '';
    gatos.forEach(gato => {
        const li = document.createElement('li');
        const nombre = gato.nombre;
        const npatas = gato.npatas;
        li.innerHTML = `
            <span>${nombre} tiene ${npatas} patas</span>
            <div>
                <button onclick="editarGato(${gato})">Editar</button>
                <button onclick="eliminarGato(${gato.id})">Eliminar</button>
            </div>
        `;

        listGatos.appendChild(li);
        
    });
}