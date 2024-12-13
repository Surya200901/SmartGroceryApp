async function fetchItems() {
    const response = await fetch('/api/grocery');
    const items = await response.json();
    const list = document.getElementById('groceryList');
    list.innerHTML = '';
    items.forEach(item => {
        const li = document.createElement('li');
        li.textContent = `${item.name} - ${item.quantity}`;
        list.appendChild(li);
    });
}

async function addItem() {
    const name = document.getElementById('itemName').value;
    const quantity = document.getElementById('itemQuantity').value;
    await fetch('/api/grocery', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name, quantity, inStock: true })
    });
    fetchItems();
}

fetchItems();
