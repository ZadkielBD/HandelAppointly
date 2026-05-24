function initSearchSelect(config) {
    const {
        wrapperId,
        dataId,
        inputId,
        inputRowId,
        dropdownId,
        hiddenFieldsId,
        fieldName,
        badgeText,
        icon,
        multiple = true,
    } = config;

    const items = Array.from(document.querySelectorAll(`#${dataId} div`)).map(el => ({
        id: el.dataset.id,
        nombre: el.dataset.nombre,
    })).sort((a, b) => a.nombre.localeCompare(b.nombre, 'es'));

    let selected = [];
    let focusedIdx = -1;
    let filtered = [...items];

    const input = document.getElementById(inputId);
    const dropdown = document.getElementById(dropdownId);
    const inputRow = document.getElementById(inputRowId);
    const hiddenFields = document.getElementById(hiddenFieldsId);
    const wrapper = document.getElementById(wrapperId);

    // Marca el wrapper si es múltiple
    if (multiple) {
        wrapper.classList.add('multiple');
    }

    // Agrega el ícono al input row
    const arrow = document.createElement('span');
    arrow.className = 'material-symbols-outlined ss-arrow';
    arrow.textContent = multiple ? 'search' : 'expand_more';
    inputRow.appendChild(arrow);

    function renderDropdown() {
        const q = input.value.toLowerCase().trim();
        filtered = items.filter(e => e.nombre.toLowerCase().includes(q));
        focusedIdx = -1;

        if (!filtered.length) {
            dropdown.innerHTML = '<div class="ss-empty">Sin resultados</div>';
            return;
        }

        dropdown.innerHTML = filtered.map((e, i) => {
            const isSel = selected.some(s => s.id === e.id);
            return `<div class="ss-option${isSel ? ' selected' : ''}" data-idx="${i}">
                <span class="ss-opt-icon">${icon}</span>
                <span class="ss-opt-name">${e.nombre}</span>
                <span class="ss-opt-badge">${badgeText}</span>
                <span class="ss-opt-check material-symbols-outlined">check</span>
            </div>`;
        }).join('');

        dropdown.querySelectorAll('.ss-option').forEach((el, i) => {
            el.addEventListener('mousedown', e => {
                e.preventDefault();
                toggleOption(filtered[i]);
            });
        });
    }

    function toggleOption(item) {
        if (!multiple) {
            selected = [item];
            input.value = item.nombre;
            dropdown.classList.remove('open');
        } else {
            const idx = selected.findIndex(s => s.id === item.id);
            if (idx >= 0) {
                selected.splice(idx, 1);
            } else {
                selected.push(item);
            }
            input.value = '';
            input.focus();
        }
        renderTags();
        renderDropdown();
        updateHidden();
        actualizarLabel();
    }

    function renderTags() {
        inputRow.querySelectorAll('.ss-tag').forEach(t => t.remove());

        if (!multiple && selected.length > 0) return;

        selected.forEach(item => {
            const tag = document.createElement('div');
            tag.className = 'ss-tag';
            tag.innerHTML = `<span>${item.nombre}</span>
                             <button type="button">
                                 <span class="material-symbols-outlined"
                                       style="font-size:16px; pointer-events:none;">
                                     close
                                 </span>
                             </button>`;
            tag.querySelector('button').addEventListener('click', () => toggleOption(item));
            inputRow.insertBefore(tag, input);
        });
    }

    function updateHidden() {
        hiddenFields.innerHTML = selected.map(e =>
            `<input type="hidden" name="${fieldName}" value="${e.id}">`
        ).join('');
    }

    function actualizarLabel() {
        if (selected.length > 0 || input.value !== '') {
            wrapper.classList.add('activo');
        } else {
            wrapper.classList.remove('activo');
        }
    }

    input.addEventListener('focus', () => {
        renderDropdown();
        dropdown.classList.add('open');
        wrapper.classList.add('activo');
        wrapper.classList.add('enfocado');
    });

    input.addEventListener('blur', () => {
        wrapper.classList.remove('enfocado');
        actualizarLabel();
    });

    input.addEventListener('input', () => {
        renderDropdown();
        dropdown.classList.add('open');
        actualizarLabel();
    });

    input.addEventListener('keydown', e => {
        const opts = dropdown.querySelectorAll('.ss-option');
        if (e.key === 'ArrowDown') {
            e.preventDefault();
            focusedIdx = Math.min(focusedIdx + 1, opts.length - 1);
            opts.forEach((o, i) => o.classList.toggle('focused', i === focusedIdx));
        } else if (e.key === 'ArrowUp') {
            e.preventDefault();
            focusedIdx = Math.max(focusedIdx - 1, 0);
            opts.forEach((o, i) => o.classList.toggle('focused', i === focusedIdx));
        } else if (e.key === 'Enter' && focusedIdx >= 0) {
            e.preventDefault();
            toggleOption(filtered[focusedIdx]);
        } else if (e.key === 'Escape') {
            dropdown.classList.remove('open');
        }
    });

    document.addEventListener('mousedown', e => {
        if (!wrapper.contains(e.target)) {
            dropdown.classList.remove('open');
        }
    });

    renderDropdown();
}