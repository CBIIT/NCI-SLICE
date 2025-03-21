window.createChevronUpSvg = function (id, width, height) {
    var chevronUpSvg = document.createElementNS(
        "http://www.w3.org/2000/svg",
        "svg"
    );
    chevronUpSvg.setAttribute("xmlns", "http://www.w3.org/2000/svg");
    chevronUpSvg.setAttribute("id", id);
    chevronUpSvg.setAttributeNS(null, "width", width);
    chevronUpSvg.setAttributeNS(null, "height", height);
    chevronUpSvg.setAttributeNS(null, "fill", "currentColor");
    chevronUpSvg.setAttributeNS(null, "class", "bi bi-chevron-up");
    chevronUpSvg.setAttributeNS(null, "viewBox", "0 0 " + width + " " + height);

    var chevronUpSvgPath1 = document.createElementNS(
        "http://www.w3.org/2000/svg",
        "path"
    );
    chevronUpSvgPath1.setAttributeNS(null, "fill-rule", "evenodd");
    chevronUpSvgPath1.setAttributeNS(
        null,
        "d",
        "M7.646 4.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1-.708.708L8 5.707l-5.646 5.647a.5.5 0 0 1-.708-.708l6-6z"
    );
    chevronUpSvg.appendChild(chevronUpSvgPath1);
    return chevronUpSvg;
}

window.createChevronDownSvg = function (id, width, height) {
    var chevronDownSvg = document.createElementNS(
        "http://www.w3.org/2000/svg",
        "svg"
    );
    chevronDownSvg.setAttribute("xmlns", "http://www.w3.org/2000/svg");
    chevronDownSvg.setAttribute("id", id);
    chevronDownSvg.setAttributeNS(null, "width", width);
    chevronDownSvg.setAttributeNS(null, "height", height);
    chevronDownSvg.setAttributeNS(null, "fill", "currentColor");
    chevronDownSvg.setAttributeNS(null, "class", "bi bi-chevron-down");
    chevronDownSvg.setAttributeNS(null, "viewBox", "0 0 " + width + " " + height);

    var chevronDownSvgPath1 = document.createElementNS(
        "http://www.w3.org/2000/svg",
        "path"
    );
    chevronDownSvgPath1.setAttributeNS(null, "fill-rule", "evenodd");
    chevronDownSvgPath1.setAttributeNS(
        null,
        "d",
        "M1.646 4.646a.5.5 0 0 1 .708 0L8 10.293l5.646-5.647a.5.5 0 0 1 .708.708l-6 6a.5.5 0 0 1-.708 0l-6-6a.5.5 0 0 1 0-.708z"
    );
    chevronDownSvg.appendChild(chevronDownSvgPath1);
    return chevronDownSvg;
}

window.createRoundPlusSvg = function (id, width, height) {
    var roundPlusSvg = document.createElementNS(
        "http://www.w3.org/2000/svg",
        "svg"
    );
    roundPlusSvg.setAttribute("xmlns", "http://www.w3.org/2000/svg");
    roundPlusSvg.setAttribute("id", id);
    roundPlusSvg.setAttributeNS(null, "width", width);
    roundPlusSvg.setAttributeNS(null, "height", height);
    roundPlusSvg.setAttributeNS(null, "fill", "currentColor");
    roundPlusSvg.setAttributeNS(null, "class", "bi bi-plus-circle");
    roundPlusSvg.setAttributeNS(null, "viewBox", "0 0 " + width + " " + height);

    var roundPlusSvgPath1 = document.createElementNS(
        "http://www.w3.org/2000/svg",
        "path"
    );
    roundPlusSvgPath1.setAttributeNS(
        null,
        "d",
        "M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"
    );
    roundPlusSvg.appendChild(roundPlusSvgPath1);

    var roundPlusSvgPath2 = document.createElementNS(
        "http://www.w3.org/2000/svg",
        "path"
    );
    roundPlusSvgPath2.setAttributeNS(
        null,
        "d",
        "M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"
    );
    roundPlusSvg.appendChild(roundPlusSvgPath2);
    return roundPlusSvg;
}

window.createRoundDeleteSvg = function (id, width, height) {
    var roundDeleteSvg = document.createElementNS(
        "http://www.w3.org/2000/svg",
        "svg"
    );
    roundDeleteSvg.setAttribute("xmlns", "http://www.w3.org/2000/svg");
    roundDeleteSvg.setAttribute("id", id);
    roundDeleteSvg.setAttributeNS(null, "width", width);
    roundDeleteSvg.setAttributeNS(null, "height", height);
    roundDeleteSvg.setAttributeNS(null, "fill", "currentColor");
    roundDeleteSvg.setAttributeNS(null, "class", "bi bi-x-circle");
    roundDeleteSvg.setAttributeNS(null, "viewBox", "0 0 " + width + " " + height);

    var roundDeleteSvgPath1 = document.createElementNS(
        "http://www.w3.org/2000/svg",
        "path"
    );
    roundDeleteSvgPath1.setAttributeNS(
        null,
        "d",
        "M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"
    );
    roundDeleteSvg.appendChild(roundDeleteSvgPath1);

    var roundDeleteSvgPath2 = document.createElementNS(
        "http://www.w3.org/2000/svg",
        "path"
    );
    roundDeleteSvgPath2.setAttributeNS(
        null,
        "d",
        "M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"
    );
    roundDeleteSvg.appendChild(roundDeleteSvgPath2);
    return roundDeleteSvg;
}