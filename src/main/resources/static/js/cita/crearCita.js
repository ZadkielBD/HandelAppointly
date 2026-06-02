// Solo inicializa el buscador de doctor si no viene preseleccionado
if (document.getElementById('doctorWrap')) {
    initSearchSelect({
        wrapperId: 'doctorWrap',
        dataId: 'doctorData',
        inputId: 'doctorInput',
        inputRowId: 'doctorInputRow',
        dropdownId: 'doctorDropdown',
        hiddenFieldsId: 'doctorHiddenFields',
        fieldName: 'doctorId',
        badgeText: 'Doctor',
        icon: '👨‍⚕️',
        multiple: false
    });
}

initSearchSelect({
    wrapperId: 'tipoConsultaWrap',
    dataId: 'tipoConsultaData',
    inputId: 'tipoConsultaInput',
    inputRowId: 'tipoConsultaInputRow',
    dropdownId: 'tipoConsultaDropdown',
    hiddenFieldsId: 'tipoConsultaHiddenFields',
    fieldName: 'consulta',
    badgeText: 'Tipo',
    icon: '🏥',
    multiple: false
});