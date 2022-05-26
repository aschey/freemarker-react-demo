<#if dev!false == true>
<#include "devHeader.html">
<#else>
<#include "header.html">
</#if>

<script>
var numRequests = ${model["numRequests"]};
let id = document.getElementById.bind(document);

window.addCar = (make, model) => {
  id('make').value = make;
  id('model').value = model;
  id('addCar').submit();
}

window.setSecret = (secret) => {
  id('secret').value = secret;
}
</script>

<div id="header">
  <h2>FreeMarker Spring MVC Hello World</h2>
</div>
<div id="root"></div>

<div id="content">
  <fieldset>
    <legend>Add Car</legend>
    <form name="car" id='addCar' action="add" method="post">
      Make : <input id="make" type="text" name="make" /><br/>
      Model: <input id="model" type="text" name="model" /><br/>
      <input id="secret" type="hidden" name="secret" />
      <input type="submit" value="Save" />
    </form>
  </fieldset>
  <br/>
  <table class="datatable">
    <tr>
      <th>Make</th>
      <th>Model</th>
      <th>Secret</th>
    </tr>
    <#list model["carList"] as car>
      <tr>
        <td>${car.make}</td>
        <td>${car.model}</td>
        <td>${car.secret}</td>
      </tr>
    </#list>
  </table>
</div>