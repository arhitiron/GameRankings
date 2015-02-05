${user.id}. <#if ((params.name)?? || !params??)>${user.name},</#if> <#if ((params.gamesPlayed)?? || !params??)>games played:  ${user.gamesPlayed},</#if> <#if ((params.wins)?? || !params??)>wins: ${user.wins},</#if> percent wins: <#if user.gamesPlayed gt 0>${(user.wins / user.gamesPlayed) * 100}%<#else>-</#if>  
<#if ((params.whoWon)?? || !params??)>
 Who win: <#include "property/whoWon.ftl"> 
</#if>
<#if ((params.whoLost)?? || !params??)>
 Who lost: <#include "property/whoLost.ftl"> 
</#if> 
<#if ((params.withWhomPlayed)?? || !params??)>
 With whom played: <#include "property/withWhomPlayed.ftl"> 
</#if>
<#if ((params.withWhomShouldPlay)?? || !params??)>
 With whom should play: <#include "property/withWhomShouldPlay.ftl"> 
</#if>