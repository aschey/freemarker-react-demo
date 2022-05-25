<#if dev!false == true>
<#include "devHeader.html">
<#else>
<#include "header.html">
</#if>
<script>
window.addCar = (make, model) => {
  let id = document.getElementById.bind(document);
  id('make').value = make;
  id('model').value = model;
  id('addCar').submit();
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
      <input type="submit" value="Save" />
    </form>
  </fieldset>
  <br/>
  <table class="datatable">
    <tr>
      <th>Make</th>
      <th>Model</th>
    </tr>
    <#list model["carList"] as car>
      <tr>
        <td>${car.make}</td>
        <td>${car.model}</td>
      </tr>
    </#list>
  </table>
</div>