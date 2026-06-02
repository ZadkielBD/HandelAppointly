initSearchSelect({
    wrapperId: 'especialidadWrap',
    dataId: 'especialidadData',
    inputId: 'especialidadInput',
    inputRowId: 'especialidadInputRow',
    dropdownId: 'especialidadDropdown',
    hiddenFieldsId: 'especialidadHiddenFields',
    fieldName: 'especialidad',
    badgeText: 'Especialidad',
    icon: '🩺',
    multiple: false
});
 
// Auto-submit al seleccionar una opción
document.getElementById('especialidadDropdown').addEventListener('mousedown', () => {
    setTimeout(() => {
        document.getElementById('filtroEspecialidadForm').submit();
    }, 50);
});