<template>
    <svg width="1300" height="650"></svg>

</template>
<script>
/* eslint-disable */
import * as d3 from "d3";
const data = {
  nodes: [
    { id: "Myriel", group: 1, image:"../../static/paper.png" },
    { id: "Napoleon", group: 1, image:"../../static/paper.png" },
    { id: "Mlle.Baptistine", group: 1, image:"../../static/paper.png" },
    { id: "Mme.Magloire", group: 1, image:"../../static/paper.png" },
    { id: "CountessdeLo", group: 1, image:"../../static/paper.png" },
    { id: "Geborand", group: 1, image:"../../static/paper.png" },
    { id: "Champtercier", group: 1, image:"../../static/paper.png" },
    { id: "Cravatte", group: 1, image:"../../static/paper.png" },
    { id: "Count", group: 1, image:"../../static/paper.png" },
    { id: "OldMan", group: 1, image:"../../static/paper.png" },
    { id: "Labarre", group: 2, image:"../../static/paper.png" },
    { id: "Valjean", group: 2, image:"../../static/paper.png" },
    { id: "Marguerite", group: 3, image:"../../static/paper.png" },
    { id: "Mme.deR", group: 2, image:"../../static/paper.png" },
    { id: "Isabeau", group: 2, image:"../../static/paper.png" },
    { id: "Gervais", group: 2, image:"../../static/paper.png" },
    { id: "Tholomyes", group: 3, image:"../../static/paper.png" },
    { id: "Listolier", group: 3, image:"../../static/paper.png" },
    { id: "Fameuil", group: 3, image:"../../static/paper.png" },
    { id: "Blacheville", group: 3, image:"../../static/paper.png" },
    { id: "Favourite", group: 3, image:"../../static/paper.png" },
    { id: "Dahlia", group: 3, image:"../../static/paper.png" },
    { id: "Zephine", group: 3, image:"../../static/paper.png" },
    { id: "Fantine", group: 3, image:"../../static/paper.png" },
    { id: "Mme.Thenardier", group: 4, image:"../../static/paper.png" },
    { id: "Thenardier", group: 4, image:"../../static/paper.png" },
    { id: "Cosette", group: 5, image:"../../static/paper.png" },
    { id: "Javert", group: 4, image:"../../static/paper.png" },
    { id: "Fauchelevent", group: 0, image:"../../static/paper.png" },
    { id: "Bamatabois", group: 2, image:"../../static/paper.png" },
    { id: "Perpetue", group: 3, image:"../../static/paper.png" },
    { id: "Simplice", group: 2, image:"../../static/paper.png" },
    { id: "Scaufflaire", group: 2, image:"../../static/paper.png" },
    { id: "Woman1", group: 2, image:"../../static/paper.png" },
    { id: "Judge", group: 2, image:"../../static/paper.png" },
    { id: "Champmathieu", group: 2, image:"../../static/paper.png" },
    { id: "Brevet", group: 2, image:"../../static/paper.png" },
    { id: "Chenildieu", group: 2, image:"../../static/paper.png" },
    { id: "Cochepaille", group: 2, image:"../../static/paper.png" },
    { id: "Pontmercy", group: 4, image:"../../static/paper.png" },
    { id: "Boulatruelle", group: 6, image:"../../static/paper.png" },
    { id: "Eponine", group: 4, image:"../../static/paper.png" },
    { id: "Anzelma", group: 4, image:"../../static/paper.png" },
    { id: "Woman2", group: 5, image:"../../static/paper.png" },
    { id: "MotherInnocent", group: 0, image:"../../static/paper.png" },
    { id: "Gribier", group: 0, image:"../../static/paper.png" },
    { id: "Jondrette", group: 7, image:"../../static/paper.png" },
    { id: "Mme.Burgon", group: 7, image:"../../static/paper.png" },
    { id: "Gavroche", group: 8, image:"../../static/paper.png" },
    { id: "Gillenormand", group: 5, image:"../../static/paper.png" },
    { id: "Magnon", group: 5, image:"../../static/paper.png" },
    { id: "Mlle.Gillenormand", group: 5, image:"../../static/paper.png" },
    { id: "Mme.Pontmercy", group: 5, image:"../../static/paper.png" },
    { id: "Mlle.Vaubois", group: 5, image:"../../static/paper.png" },
    { id: "Lt.Gillenormand", group: 5, image:"../../static/paper.png" },
    { id: "Marius", group: 8, image:"../../static/paper.png" },
    { id: "BaronessT", group: 5, image:"../../static/paper.png" },
    { id: "Mabeuf", group: 8, image:"../../static/paper.png" },
    { id: "Enjolras", group: 8, image:"../../static/paper.png" },
    { id: "Combeferre", group: 8, image:"../../static/paper.png" },
    { id: "Prouvaire", group: 8, image:"../../static/paper.png" },
    { id: "Feuilly", group: 8, image:"../../static/paper.png" },
    { id: "Courfeyrac", group: 8, image:"../../static/paper.png" },
    { id: "Bahorel", group: 8, image:"../../static/paper.png" },
    { id: "Bossuet", group: 8, image:"../../static/paper.png" },
    { id: "Joly", group: 8, image:"../../static/paper.png" },
    { id: "Grantaire", group: 8, image:"../../static/paper.png" },
    { id: "MotherPlutarch", group: 9, image:"../../static/paper.png" },
    { id: "Gueulemer", group: 4, image:"../../static/paper.png" },
    { id: "Babet", group: 4, image:"../../static/paper.png" },
    { id: "Claquesous", group: 4, image:"../../static/paper.png" },
    { id: "Montparnasse", group: 4, image:"../../static/paper.png" },
    { id: "Toussaint", group: 5, image:"../../static/paper.png" },
    { id: "Child1", group: 10, image:"../../static/paper.png" },
    { id: "Child2", group: 10, image:"../../static/paper.png" },
    { id: "Brujon", group: 4, image:"../../static/paper.png" },
    { id: "Mme.Hucheloup", group: 8, image:"../../static/paper.png" }
  ],
  links: [
    { source: "Napoleon", target: "Myriel", value: 1 },
    { source: "Mlle.Baptistine", target: "Myriel", value: 8 },
    { source: "Mme.Magloire", target: "Myriel", value: 10 },
    { source: "Mme.Magloire", target: "Mlle.Baptistine", value: 6 },
    { source: "CountessdeLo", target: "Myriel", value: 1 },
    { source: "Geborand", target: "Myriel", value: 1 },
    { source: "Champtercier", target: "Myriel", value: 1 },
    { source: "Cravatte", target: "Myriel", value: 1 },
    { source: "Count", target: "Myriel", value: 2 },
    { source: "OldMan", target: "Myriel", value: 1 },
    { source: "Valjean", target: "Labarre", value: 1 },
    { source: "Valjean", target: "Mme.Magloire", value: 3 },
    { source: "Valjean", target: "Mlle.Baptistine", value: 3 },
    { source: "Valjean", target: "Myriel", value: 5 },
    { source: "Marguerite", target: "Valjean", value: 1 },
    { source: "Mme.deR", target: "Valjean", value: 1 },
    { source: "Isabeau", target: "Valjean", value: 1 },
    { source: "Gervais", target: "Valjean", value: 1 },
    { source: "Listolier", target: "Tholomyes", value: 4 },
    { source: "Fameuil", target: "Tholomyes", value: 4 },
    { source: "Fameuil", target: "Listolier", value: 4 },
    { source: "Blacheville", target: "Tholomyes", value: 4 },
    { source: "Blacheville", target: "Listolier", value: 4 },
    { source: "Blacheville", target: "Fameuil", value: 4 },
    { source: "Favourite", target: "Tholomyes", value: 3 },
    { source: "Favourite", target: "Listolier", value: 3 },
    { source: "Favourite", target: "Fameuil", value: 3 },
    { source: "Favourite", target: "Blacheville", value: 4 },
    { source: "Dahlia", target: "Tholomyes", value: 3 },
    { source: "Dahlia", target: "Listolier", value: 3 },
    { source: "Dahlia", target: "Fameuil", value: 3 },
    { source: "Dahlia", target: "Blacheville", value: 3 },
    { source: "Dahlia", target: "Favourite", value: 5 },
    { source: "Zephine", target: "Tholomyes", value: 3 },
    { source: "Zephine", target: "Listolier", value: 3 },
    { source: "Zephine", target: "Fameuil", value: 3 },
    { source: "Zephine", target: "Blacheville", value: 3 },
    { source: "Zephine", target: "Favourite", value: 4 },
    { source: "Zephine", target: "Dahlia", value: 4 },
    { source: "Fantine", target: "Tholomyes", value: 3 },
    { source: "Fantine", target: "Listolier", value: 3 },
    { source: "Fantine", target: "Fameuil", value: 3 },
    { source: "Fantine", target: "Blacheville", value: 3 },
    { source: "Fantine", target: "Favourite", value: 4 },
    { source: "Fantine", target: "Dahlia", value: 4 },
    { source: "Fantine", target: "Zephine", value: 4 },
    { source: "Fantine", target: "Marguerite", value: 2 },
    { source: "Fantine", target: "Valjean", value: 9 },
    { source: "Mme.Thenardier", target: "Fantine", value: 2 },
    { source: "Mme.Thenardier", target: "Valjean", value: 7 },
    { source: "Thenardier", target: "Mme.Thenardier", value: 13 },
    { source: "Thenardier", target: "Fantine", value: 1 },
    { source: "Thenardier", target: "Valjean", value: 12 },
    { source: "Cosette", target: "Mme.Thenardier", value: 4 },
    { source: "Cosette", target: "Valjean", value: 31 },
    { source: "Cosette", target: "Tholomyes", value: 1 },
    { source: "Cosette", target: "Thenardier", value: 1 },
    { source: "Javert", target: "Valjean", value: 17 },
    { source: "Javert", target: "Fantine", value: 5 },
    { source: "Javert", target: "Thenardier", value: 5 },
    { source: "Javert", target: "Mme.Thenardier", value: 1 },
    { source: "Javert", target: "Cosette", value: 1 },
    { source: "Fauchelevent", target: "Valjean", value: 8 },
    { source: "Fauchelevent", target: "Javert", value: 1 },
    { source: "Bamatabois", target: "Fantine", value: 1 },
    { source: "Bamatabois", target: "Javert", value: 1 },
    { source: "Bamatabois", target: "Valjean", value: 2 },
    { source: "Perpetue", target: "Fantine", value: 1 },
    { source: "Simplice", target: "Perpetue", value: 2 },
    { source: "Simplice", target: "Valjean", value: 3 },
    { source: "Simplice", target: "Fantine", value: 2 },
    { source: "Simplice", target: "Javert", value: 1 },
    { source: "Scaufflaire", target: "Valjean", value: 1 },
    { source: "Woman1", target: "Valjean", value: 2 },
    { source: "Woman1", target: "Javert", value: 1 },
    { source: "Judge", target: "Valjean", value: 3 },
    { source: "Judge", target: "Bamatabois", value: 2 },
    { source: "Champmathieu", target: "Valjean", value: 3 },
    { source: "Champmathieu", target: "Judge", value: 3 },
    { source: "Champmathieu", target: "Bamatabois", value: 2 },
    { source: "Brevet", target: "Judge", value: 2 },
    { source: "Brevet", target: "Champmathieu", value: 2 },
    { source: "Brevet", target: "Valjean", value: 2 },
    { source: "Brevet", target: "Bamatabois", value: 1 },
    { source: "Chenildieu", target: "Judge", value: 2 },
    { source: "Chenildieu", target: "Champmathieu", value: 2 },
    { source: "Chenildieu", target: "Brevet", value: 2 },
    { source: "Chenildieu", target: "Valjean", value: 2 },
    { source: "Chenildieu", target: "Bamatabois", value: 1 },
    { source: "Cochepaille", target: "Judge", value: 2 },
    { source: "Cochepaille", target: "Champmathieu", value: 2 },
    { source: "Cochepaille", target: "Brevet", value: 2 },
    { source: "Cochepaille", target: "Chenildieu", value: 2 },
    { source: "Cochepaille", target: "Valjean", value: 2 },
    { source: "Cochepaille", target: "Bamatabois", value: 1 },
    { source: "Pontmercy", target: "Thenardier", value: 1 },
    { source: "Boulatruelle", target: "Thenardier", value: 1 },
    { source: "Eponine", target: "Mme.Thenardier", value: 2 },
    { source: "Eponine", target: "Thenardier", value: 3 },
    { source: "Anzelma", target: "Eponine", value: 2 },
    { source: "Anzelma", target: "Thenardier", value: 2 },
    { source: "Anzelma", target: "Mme.Thenardier", value: 1 },
    { source: "Woman2", target: "Valjean", value: 3 },
    { source: "Woman2", target: "Cosette", value: 1 },
    { source: "Woman2", target: "Javert", value: 1 },
    { source: "MotherInnocent", target: "Fauchelevent", value: 3 },
    { source: "MotherInnocent", target: "Valjean", value: 1 },
    { source: "Gribier", target: "Fauchelevent", value: 2 },
    { source: "Mme.Burgon", target: "Jondrette", value: 1 },
    { source: "Gavroche", target: "Mme.Burgon", value: 2 },
    { source: "Gavroche", target: "Thenardier", value: 1 },
    { source: "Gavroche", target: "Javert", value: 1 },
    { source: "Gavroche", target: "Valjean", value: 1 },
    { source: "Gillenormand", target: "Cosette", value: 3 },
    { source: "Gillenormand", target: "Valjean", value: 2 },
    { source: "Magnon", target: "Gillenormand", value: 1 },
    { source: "Magnon", target: "Mme.Thenardier", value: 1 },
    { source: "Mlle.Gillenormand", target: "Gillenormand", value: 9 },
    { source: "Mlle.Gillenormand", target: "Cosette", value: 2 },
    { source: "Mlle.Gillenormand", target: "Valjean", value: 2 },
    { source: "Mme.Pontmercy", target: "Mlle.Gillenormand", value: 1 },
    { source: "Mme.Pontmercy", target: "Pontmercy", value: 1 },
    { source: "Mlle.Vaubois", target: "Mlle.Gillenormand", value: 1 },
    { source: "Lt.Gillenormand", target: "Mlle.Gillenormand", value: 2 },
    { source: "Lt.Gillenormand", target: "Gillenormand", value: 1 },
    { source: "Lt.Gillenormand", target: "Cosette", value: 1 },
    { source: "Marius", target: "Mlle.Gillenormand", value: 6 },
    { source: "Marius", target: "Gillenormand", value: 12 },
    { source: "Marius", target: "Pontmercy", value: 1 },
    { source: "Marius", target: "Lt.Gillenormand", value: 1 },
    { source: "Marius", target: "Cosette", value: 21 },
    { source: "Marius", target: "Valjean", value: 19 },
    { source: "Marius", target: "Tholomyes", value: 1 },
    { source: "Marius", target: "Thenardier", value: 2 },
    { source: "Marius", target: "Eponine", value: 5 },
    { source: "Marius", target: "Gavroche", value: 4 },
    { source: "BaronessT", target: "Gillenormand", value: 1 },
    { source: "BaronessT", target: "Marius", value: 1 },
    { source: "Mabeuf", target: "Marius", value: 1 },
    { source: "Mabeuf", target: "Eponine", value: 1 },
    { source: "Mabeuf", target: "Gavroche", value: 1 },
    { source: "Enjolras", target: "Marius", value: 7 },
    { source: "Enjolras", target: "Gavroche", value: 7 },
    { source: "Enjolras", target: "Javert", value: 6 },
    { source: "Enjolras", target: "Mabeuf", value: 1 },
    { source: "Enjolras", target: "Valjean", value: 4 },
    { source: "Combeferre", target: "Enjolras", value: 15 },
    { source: "Combeferre", target: "Marius", value: 5 },
    { source: "Combeferre", target: "Gavroche", value: 6 },
    { source: "Combeferre", target: "Mabeuf", value: 2 },
    { source: "Prouvaire", target: "Gavroche", value: 1 },
    { source: "Prouvaire", target: "Enjolras", value: 4 },
    { source: "Prouvaire", target: "Combeferre", value: 2 },
    { source: "Feuilly", target: "Gavroche", value: 2 },
    { source: "Feuilly", target: "Enjolras", value: 6 },
    { source: "Feuilly", target: "Prouvaire", value: 2 },
    { source: "Feuilly", target: "Combeferre", value: 5 },
    { source: "Feuilly", target: "Mabeuf", value: 1 },
    { source: "Feuilly", target: "Marius", value: 1 },
    { source: "Courfeyrac", target: "Marius", value: 9 },
    { source: "Courfeyrac", target: "Enjolras", value: 17 },
    { source: "Courfeyrac", target: "Combeferre", value: 13 },
    { source: "Courfeyrac", target: "Gavroche", value: 7 },
    { source: "Courfeyrac", target: "Mabeuf", value: 2 },
    { source: "Courfeyrac", target: "Eponine", value: 1 },
    { source: "Courfeyrac", target: "Feuilly", value: 6 },
    { source: "Courfeyrac", target: "Prouvaire", value: 3 },
    { source: "Bahorel", target: "Combeferre", value: 5 },
    { source: "Bahorel", target: "Gavroche", value: 5 },
    { source: "Bahorel", target: "Courfeyrac", value: 6 },
    { source: "Bahorel", target: "Mabeuf", value: 2 },
    { source: "Bahorel", target: "Enjolras", value: 4 },
    { source: "Bahorel", target: "Feuilly", value: 3 },
    { source: "Bahorel", target: "Prouvaire", value: 2 },
    { source: "Bahorel", target: "Marius", value: 1 },
    { source: "Bossuet", target: "Marius", value: 5 },
    { source: "Bossuet", target: "Courfeyrac", value: 12 },
    { source: "Bossuet", target: "Gavroche", value: 5 },
    { source: "Bossuet", target: "Bahorel", value: 4 },
    { source: "Bossuet", target: "Enjolras", value: 10 },
    { source: "Bossuet", target: "Feuilly", value: 6 },
    { source: "Bossuet", target: "Prouvaire", value: 2 },
    { source: "Bossuet", target: "Combeferre", value: 9 },
    { source: "Bossuet", target: "Mabeuf", value: 1 },
    { source: "Bossuet", target: "Valjean", value: 1 },
    { source: "Joly", target: "Bahorel", value: 5 },
    { source: "Joly", target: "Bossuet", value: 7 },
    { source: "Joly", target: "Gavroche", value: 3 },
    { source: "Joly", target: "Courfeyrac", value: 5 },
    { source: "Joly", target: "Enjolras", value: 5 },
    { source: "Joly", target: "Feuilly", value: 5 },
    { source: "Joly", target: "Prouvaire", value: 2 },
    { source: "Joly", target: "Combeferre", value: 5 },
    { source: "Joly", target: "Mabeuf", value: 1 },
    { source: "Joly", target: "Marius", value: 2 },
    { source: "Grantaire", target: "Bossuet", value: 3 },
    { source: "Grantaire", target: "Enjolras", value: 3 },
    { source: "Grantaire", target: "Combeferre", value: 1 },
    { source: "Grantaire", target: "Courfeyrac", value: 2 },
    { source: "Grantaire", target: "Joly", value: 2 },
    { source: "Grantaire", target: "Gavroche", value: 1 },
    { source: "Grantaire", target: "Bahorel", value: 1 },
    { source: "Grantaire", target: "Feuilly", value: 1 },
    { source: "Grantaire", target: "Prouvaire", value: 1 },
    { source: "MotherPlutarch", target: "Mabeuf", value: 3 },
    { source: "Gueulemer", target: "Thenardier", value: 5 },
    { source: "Gueulemer", target: "Valjean", value: 1 },
    { source: "Gueulemer", target: "Mme.Thenardier", value: 1 },
    { source: "Gueulemer", target: "Javert", value: 1 },
    { source: "Gueulemer", target: "Gavroche", value: 1 },
    { source: "Gueulemer", target: "Eponine", value: 1 },
    { source: "Babet", target: "Thenardier", value: 6 },
    { source: "Babet", target: "Gueulemer", value: 6 },
    { source: "Babet", target: "Valjean", value: 1 },
    { source: "Babet", target: "Mme.Thenardier", value: 1 },
    { source: "Babet", target: "Javert", value: 2 },
    { source: "Babet", target: "Gavroche", value: 1 },
    { source: "Babet", target: "Eponine", value: 1 },
    { source: "Claquesous", target: "Thenardier", value: 4 },
    { source: "Claquesous", target: "Babet", value: 4 },
    { source: "Claquesous", target: "Gueulemer", value: 4 },
    { source: "Claquesous", target: "Valjean", value: 1 },
    { source: "Claquesous", target: "Mme.Thenardier", value: 1 },
    { source: "Claquesous", target: "Javert", value: 1 },
    { source: "Claquesous", target: "Eponine", value: 1 },
    { source: "Claquesous", target: "Enjolras", value: 1 },
    { source: "Montparnasse", target: "Javert", value: 1 },
    { source: "Montparnasse", target: "Babet", value: 2 },
    { source: "Montparnasse", target: "Gueulemer", value: 2 },
    { source: "Montparnasse", target: "Claquesous", value: 2 },
    { source: "Montparnasse", target: "Valjean", value: 1 },
    { source: "Montparnasse", target: "Gavroche", value: 1 },
    { source: "Montparnasse", target: "Eponine", value: 1 },
    { source: "Montparnasse", target: "Thenardier", value: 1 },
    { source: "Toussaint", target: "Cosette", value: 2 },
    { source: "Toussaint", target: "Javert", value: 1 },
    { source: "Toussaint", target: "Valjean", value: 1 },
    { source: "Child1", target: "Gavroche", value: 2 },
    { source: "Child2", target: "Gavroche", value: 2 },
    { source: "Child2", target: "Child1", value: 3 },
    { source: "Brujon", target: "Babet", value: 3 },
    { source: "Brujon", target: "Gueulemer", value: 3 },
    { source: "Brujon", target: "Thenardier", value: 3 },
    { source: "Brujon", target: "Gavroche", value: 1 },
    { source: "Brujon", target: "Eponine", value: 1 },
    { source: "Brujon", target: "Claquesous", value: 1 },
    { source: "Brujon", target: "Montparnasse", value: 1 },
    { source: "Mme.Hucheloup", target: "Bossuet", value: 1 },
    { source: "Mme.Hucheloup", target: "Joly", value: 1 },
    { source: "Mme.Hucheloup", target: "Grantaire", value: 1 },
    { source: "Mme.Hucheloup", target: "Bahorel", value: 1 },
    { source: "Mme.Hucheloup", target: "Courfeyrac", value: 1 },
    { source: "Mme.Hucheloup", target: "Gavroche", value: 1 },
    { source: "Mme.Hucheloup", target: "Enjolras", value: 1 }
  ]
};

export default {
  name: "d3-force",
  methods: {
    print_id: function(id) {
      console.log(id);
    }
  },
  mounted() {
    let this_ = this;
    var img_w = 20;
    var img_h = 20;
    var color = d3.scaleOrdinal(d3.schemeCategory10);
    var zoom = d3.zoom()
                .scaleExtent([-5,2])
                .on("zoom",zoomed)

    var svg = d3.select("svg"),
      width = +svg.attr("width"),
      height = +svg.attr("height");

    svg.call(zoom)

    function zoomed() {
      svg.selectAll("g")
      .attr("transform",  d3.event.transform)
    }
  

    var attractForce = d3
      .forceManyBody()
      .strength(200)
      .distanceMax(400)
      .distanceMin(60);
    //自定义引力
    var repelForce = d3
      .forceManyBody()
      .strength(-500)
      .distanceMax(250);
    //自定义斥力

    var simulation = d3
      .forceSimulation()
      .force(
        "link",
        d3.forceLink().id(function(d) {
          return d.id;
        })
      )
      .force("center", d3.forceCenter(width / 2, height / 2))
      // .force("attractForce",attractForce)
      .force("repelForce", repelForce);

    simulation.nodes(data.nodes).on("tick", ticked);

    simulation.force("link").links(data.links);

    var link = svg
      .append("g")
      .attr("class", "links")
      .selectAll("line")
      .data(data.links)
      .enter()
      .append("line")
      .attr("stroke-width", function(d) {
        return Math.sqrt(d.value);
      })
      .style("stroke-dasharray", function (d) {
          if (d.relation == "撰写")
            return "20,10,5,5,10";
          else if (d.relation == "其他")
            return "5,5";
      });

    var tooltip = d3.select("body")
        .append("div")
        .attr("class", "tooltip")
        .style("display", "none")

    var node = svg
      .append("g")
      .attr("class", "nodes")
      .selectAll("circle")
      .data(data.nodes)
      .enter()
      .append("circle")
      .attr("r", 20)
      .attr("fill", function(d, i) {
        return color(d.group)
        /*var defs = svg.append("defs").attr("id", "imgdefs")

        var catpattern = defs.append("pattern")
                            .attr("id","catpattern" + i)
                            .attr("height", 1)
                            .attr("width", 1)

        catpattern.append("image")
                .attr("x",-(img_w - 20))
                .attr("y", (img_h - 20))
                .attr("width", img_w*2)
                .attr("height", img_h*2)
                .attr("xlink:href", d.image)

        return "url(#catpattern" + i + ")"; */
       })
      .call(
        d3
          .drag()
          .on("start", dragstarted)
          .on("drag", dragged)
          .on("end", dragended)
      )
      .on("click", function(d) {
        this_.print_id(d.id);
      })
      .on("mouseover",function(d,i){
        tooltip.html("<p>" + d.id + "</p>")
            .transition()
            .style("left", (d.x + 50) + "px")
            .style("top", (d.y + 30) + "px")
            .style("display", "block")
      })
      .on("mouseout",function(d){
        tooltip.style("display", "none")
      })

    // var text = svg
    //   .selectAll("text")
    //   .data(data.nodes)
    //   .enter()
    //   .append("text")
    //   .attr("font-family", "sans-serif")
    //   .attr("font-size", "11px")
    //   .attr("fill", "black")
    //   .attr("fill-opacity", "0.0")

    function ticked() {
      link
        .attr("x1", function(d) {
          return d.source.x;
        })
        .attr("y1", function(d) {
          return d.source.y;
        })
        .attr("x2", function(d) {
          return d.target.x;
        })
        .attr("y2", function(d) {
          return d.target.y;
        });

      node
        .attr("cx", function(d) {
          return d.x;
        })
        .attr("cy", function(d) {
          return d.y;
        });

      // text
      //   .text(function(d) {
      //     return d.id;
      //   })
      //   .attr("x", function(d, i) {
      //     return d.x;
      //   })
      //   .attr("y", function(d) {
      //     return d.y;
      //   })
      //   .attr("dx", "-3.5")
      //   .attr("dy", "20");
    }
    function dragstarted(d) {
      if (!d3.event.active) simulation.alphaTarget(0.3).restart();
      d.fx = d.x;
      d.fy = d.y;
    }

    function dragged(d) {
      d.fx = d3.event.x;
      d.fy = d3.event.y;
      // console.log("dragged")
    }

    function dragended(d) {
      // console.log('dragended')
      if (!d3.event.active) simulation.alphaTarget(0);
      d.fx = null;
      d.fy = null;
    }
  }
};
</script>
<style>
.links line {
  stroke: #999;
  stroke-opacity: 0.6;
}

.nodes circle {
  stroke: #fff;
  stroke-width: 1.5px;
  cursor: pointer;
}

.text {
  fill-opacity: 0.0;
}

.tooltip {
    position: absolute;
    top: 100px;
    left: 100px;
    -moz-border-radius: 3px;
    border-radius: 3px;
    border: 2px solid #DDD;
    background: #fff;
    opacity: 1;
    color: #000;
    padding: 10px;
    width: 300px;
    font-size: 15px;
    z-index: 120;
  }

  .tooltip p {
    font-size: 15px;
    text-align: center;
    padding: 0;
    margin: 0;
  }

  hr.tooltip-hr {
    padding: 3px 0 0 0;
    margin: 3px 0 3px 0;
  }

  .tooltip .title {
    font-size: 20px;
    line-height: 24px;
  }

  .tooltip .name {
    font-weight: bold;
  }
</style>
